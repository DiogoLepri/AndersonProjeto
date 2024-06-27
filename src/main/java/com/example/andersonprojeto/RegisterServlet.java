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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String crm = request.getParameter("crm");
        String password = request.getParameter("password");

        // Validação dos campos
        if (crm == null || crm.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Por favor, preencha todos os campos.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Validação do formato do CRM
        if (!crm.matches("\\d{4,6}")) {
            request.setAttribute("errorMessage", "CRM inválido. O CRM deve conter entre 4 e 6 dígitos.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check if the user already exists
        try (Connection connection = Database.getConnection()) {
            String checkSql = "SELECT * FROM doctors WHERE crm = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, crm);
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next()) {
                    request.setAttribute("errorMessage", "Usuário já existe. Tente um CRM diferente.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }
            }

            // Insert the new user
            String insertSql = "INSERT INTO doctors (crm, password) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, crm);
                insertStatement.setString(2, password);
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }

        response.sendRedirect("doctor-login.jsp");
    }
}
