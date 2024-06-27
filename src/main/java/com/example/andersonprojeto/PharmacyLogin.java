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

@WebServlet("/loginPh")
public class PharmacyLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigofarmacia = request.getParameter("codigofarmacia");
        String password = request.getParameter("password");

        // Debugging output
        System.out.println("Tentando logar com codigofarmacia: " + codigofarmacia);

        if (authenticateUser(codigofarmacia, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", codigofarmacia);
            System.out.println("Login bem sucedido para o codigofarmacia: " + codigofarmacia);
            response.sendRedirect("PrescriptionsFarma.jsp");
        } else {
            System.out.println("Login falhou: " + codigofarmacia);
            response.sendRedirect("farmaceutico-login.jsp?error=true");  // Redirecting with an error parameter
        }
    }

    private boolean authenticateUser(String codigofarmacia, String password) {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM pharmacies WHERE code = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, codigofarmacia);
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
