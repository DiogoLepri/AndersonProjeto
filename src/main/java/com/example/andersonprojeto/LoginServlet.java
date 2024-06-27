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
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crm = request.getParameter("crm");
        String password = request.getParameter("password");

        // Validate fields
        if (crm == null || crm.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("doctor-login.jsp?error=emptyFields");
            return;
        }

        // Validate CRM format
        if (!crm.matches("\\d{4,6}")) {
            response.sendRedirect("doctor-login.jsp?error=invalidCRM");
            return;
        }

        if (authenticateUser(crm, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", crm);
            System.out.println("Login successful for CRM: " + crm);
            response.sendRedirect("prescriptions.jsp");
        } else {
            System.out.println("Login failed for CRM: " + crm);
            response.sendRedirect("doctor-login.jsp?error=invalidCredentials");
        }
    }

    private boolean authenticateUser(String crm, String password) {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM doctors WHERE crm = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, crm);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
