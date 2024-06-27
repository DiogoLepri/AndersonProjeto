package com.example.andersonprojeto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewPrescriptions")
public class ViewPrescriptionsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String user = (session != null) ? (String) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("farmaceutico-login.jsp");
            return;
        }

        int pharmacyId = Integer.parseInt(request.getParameter("pharmacyId"));

        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM prescriptions WHERE pharmacy_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pharmacyId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Prescription prescription = new Prescription();
                    prescription.setId(resultSet.getInt("id"));
                    prescription.setDoctorName(resultSet.getString("doctor_name"));
                    prescription.setCrm(resultSet.getString("crm"));
                    prescription.setPatientName(resultSet.getString("patient_name"));
                    prescription.setCpf(resultSet.getString("cpf"));
                    prescription.setBornDate(resultSet.getString("born_date"));
                    prescription.setHealthInsurance(resultSet.getString("health_insurance"));
                    prescription.setDescription(resultSet.getString("description"));
                    prescription.setMedicineName(resultSet.getString("medicine_name"));
                    prescription.setDosage(resultSet.getString("dosage"));
                    prescription.setAmount(resultSet.getInt("amount"));
                    prescription.setAdmin(resultSet.getString("admin"));
                    prescription.setObs(resultSet.getString("obs"));
                    prescription.setPharmacyId(resultSet.getInt("pharmacy_id"));
                    prescription.setCreatedAt(resultSet.getTimestamp("created_at"));
                    prescriptions.add(prescription);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Error accessing database", e);
        }

        request.setAttribute("prescriptions", prescriptions);
        request.getRequestDispatcher("viewPrescriptions.jsp").forward(request, response);
    }
}
