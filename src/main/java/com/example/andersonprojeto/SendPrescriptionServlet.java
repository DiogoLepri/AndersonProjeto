package com.example.andersonprojeto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sendPrescription")
public class SendPrescriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doctorName = request.getParameter("doctorname");
        String crm = request.getParameter("CRM");
        String patientName = request.getParameter("name");
        String cpf = request.getParameter("cpf");
        String bornDate = request.getParameter("borndate");
        String healthInsurance = request.getParameter("healthinsurance");
        String description = request.getParameter("description");
        String medicineName = request.getParameter("medicinename");
        String dosage = request.getParameter("dosage");
        String amount = request.getParameter("amount");
        String admin = request.getParameter("Admin");
        String obs = request.getParameter("obs");
        int pharmacyId = Integer.parseInt(request.getParameter("pharmacyId"));

        try (Connection connection = Database.getConnection()) {
            String sql = "INSERT INTO prescriptions (doctor_name, crm, patient_name, cpf, born_date, health_insurance, description, medicine_name, dosage, amount, admin, obs, pharmacy_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, doctorName);
                statement.setString(2, crm);
                statement.setString(3, patientName);
                statement.setString(4, cpf);
                statement.setString(5, bornDate);
                statement.setString(6, healthInsurance);
                statement.setString(7, description);
                statement.setString(8, medicineName);
                statement.setString(9, dosage);
                statement.setString(10, amount);
                statement.setString(11, admin);
                statement.setString(12, obs);
                statement.setInt(13, pharmacyId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Error accessing database", e);
        }

        response.sendRedirect("success.jsp");
    }
}
