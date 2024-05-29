package com.example.andersonprojeto;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/loginPh")
public class PharmacyLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String password = request.getParameter("password");

        // Debugging output
        System.out.println("Tentando logar com cpf: " + cpf);

        if (authenticateUser(cpf, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", cpf);
            System.out.println("Login bem sucedido para o cpf: " + cpf);
            response.sendRedirect("PrescriptionsFarma.jsp");
        } else {
            System.out.println("Login falhou: " + cpf);
            response.sendRedirect("farmaceutico-login.jsp?error=true");  // Redirecting with an error parameter
        }
    }

    private boolean authenticateUser(String cpf, String password) {
        // Implementation should validate the cpf and password against stored data
        // This is just a placeholder for the authentication logic
        return UserStorage.getInstance().authenticateUser(cpf, password);
    }
}

