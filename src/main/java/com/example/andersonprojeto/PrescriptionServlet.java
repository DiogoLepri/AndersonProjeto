package com.example.andersonprojeto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PrescriptionServlet", urlPatterns = {"/prescriptions"})
public class PrescriptionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String cpf = request.getParameter("cpf");
        String borndate = request.getParameter("borndate");
        String healthinsurance = request.getParameter("healthinsurance");
        String doctorname = request.getParameter("doctorname");
        String crm = request.getParameter("CRM");
        String medicinename = request.getParameter("medicinename");
        String dosage = request.getParameter("dosage");
        String amount = request.getParameter("amount");
        String admin = request.getParameter("Admin");
        String obs = request.getParameter("obs");
        String pharmacyIdStr = request.getParameter("pharmacyId");

        if (name == null || name.isEmpty() ||
                description == null || description.isEmpty() ||
                cpf == null || cpf.isEmpty() ||
                borndate == null || borndate.isEmpty() ||
                healthinsurance == null || healthinsurance.isEmpty() ||
                doctorname == null || doctorname.isEmpty() ||
                crm == null || crm.isEmpty() ||
                medicinename == null || medicinename.isEmpty() ||
                dosage == null || dosage.isEmpty() ||
                amount == null || amount.isEmpty() ||
                admin == null || admin.isEmpty() ||
                pharmacyIdStr == null || pharmacyIdStr.isEmpty()) {
            request.setAttribute("error", "Por favor, preencha todos os campos obrigatórios.");
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("cpf", cpf);
            request.setAttribute("borndate", borndate);
            request.setAttribute("healthinsurance", healthinsurance);
            request.setAttribute("doctorname", doctorname);
            request.setAttribute("CRM", crm);
            request.setAttribute("medicinename", medicinename);
            request.setAttribute("dosage", dosage);
            request.setAttribute("amount", amount);
            request.setAttribute("Admin", admin);
            request.setAttribute("obs", obs);
            request.setAttribute("pharmacyId", pharmacyIdStr);
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int pharmacyId = Integer.parseInt(pharmacyIdStr);

        try (Connection connection = Database.getConnection()) {
            String sql = "INSERT INTO prescriptions (doctor_name, crm, patient_name, cpf, born_date, health_insurance, description, medicine_name, dosage, amount, admin, obs, pharmacy_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, doctorname);
                statement.setString(2, crm);
                statement.setString(3, name);
                statement.setString(4, cpf);
                statement.setString(5, borndate);
                statement.setString(6, healthinsurance);
                statement.setString(7, description);
                statement.setString(8, medicinename);
                statement.setString(9, dosage);
                statement.setInt(10, Integer.parseInt(amount));
                statement.setString(11, admin);
                statement.setString(12, obs);
                statement.setInt(13, pharmacyId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }

        response.sendRedirect("PrescriptionsFarma.jsp?pharmacyId=" + pharmacyId);
    }
}
