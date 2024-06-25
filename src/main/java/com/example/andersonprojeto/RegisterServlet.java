package com.example.andersonprojeto;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crm = request.getParameter("crm");
        String password = request.getParameter("password");
        UserStorage userStorage = UserStorage.getInstance();

        // Validação dos campos
        if (crm == null || crm.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in all fields.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Validação do formato do CRM
        if (!crm.matches("\\d{6}")) {
            request.setAttribute("errorMessage", "Invalid CRM format. CRM should contain 6 digits.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Validação do tamanho da senha
        if (password.length() < 4) {
            request.setAttribute("errorMessage", "Password should be at least 4 characters long.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!userStorage.addUser(crm, password)) {
            request.setAttribute("errorMessage", "User already exists. Try a different CRM.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            response.sendRedirect("doctor-login.jsp");
        }
    }
}

