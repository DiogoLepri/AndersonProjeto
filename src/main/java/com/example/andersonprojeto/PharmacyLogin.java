package com.example.andersonprojeto;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

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
        // Implementation should validate the codigofarmacia and password against stored data
        // This is just a placeholder for the authentication logic
        return UserStorage.getInstance().authenticateUser(codigofarmacia, password);
    }
}

