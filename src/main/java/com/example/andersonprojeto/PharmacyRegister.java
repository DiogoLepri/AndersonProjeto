package com.example.andersonprojeto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerPh")
public class PharmacyRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigofarmacia = request.getParameter("codigofarmacia");
        String password = request.getParameter("password");

        if (codigofarmacia == null || codigofarmacia.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Por favor, preencha todos os campos.");
            request.getRequestDispatcher("/farmaceutico-register.jsp").forward(request, response);
            return;
        }

        try (Connection connection = Database.getConnection()) {
            // Check if the pharmacy code already exists
            String checkSql = "SELECT * FROM pharmacies WHERE code = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, codigofarmacia);
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next()) {
                    request.setAttribute("errorMessage", "Código de farmácia já registrado. Tente um código diferente.");
                    request.getRequestDispatcher("/farmaceutico-register.jsp").forward(request, response);
                    return;
                }
            }

            // Insert the new pharmacy into the database
            String sql = "INSERT INTO pharmacies (code, password) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, codigofarmacia);
                statement.setString(2, password);
                statement.executeUpdate();
            }

            response.sendRedirect("farmaceutico-login.jsp");
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Erro ao registrar. Tente um código de farmácia diferente.");
            request.getRequestDispatcher("/farmaceutico-register.jsp").forward(request, response);
        }
    }
}
