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

@WebServlet(name = "ViewPrescriptionServlet", urlPatterns = {"/viewPrescriptions"})
class ViewPrescriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pharmacyId = request.getParameter("pharmacyId");
        List<Prescription> prescriptions = new ArrayList<>();

        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM prescriptions WHERE pharmacy_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pharmacyId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Prescription prescription = new Prescription(
                            resultSet.getString("patient_name"),
                            resultSet.getString("doctor_name"),
                            resultSet.getString("medicine_name"),
                            resultSet.getString("description"),
                            resultSet.getString("dosage"),
                            resultSet.getInt("amount"),
                            resultSet.getString("admin"),
                            resultSet.getString("obs"),
                            resultSet.getTimestamp("created_at")
                    );
                    prescriptions.add(prescription);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("prescriptions", prescriptions);
        request.getRequestDispatcher("/PrescriptionsFarma.jsp").forward(request, response);
    }
}
