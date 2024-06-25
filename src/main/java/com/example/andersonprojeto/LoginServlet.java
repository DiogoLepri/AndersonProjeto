package com.example.andersonprojeto;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crm = request.getParameter("crm");
        String password = request.getParameter("password");

        // Validação dos campos
        if (crm == null || crm.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("doctor-login.jsp?error=emptyFields");
            return;
        }

        // Validação do formato do CRM
        if (!crm.matches("\\d{6}")) {
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
        return UserStorage.getInstance().authenticateUser(crm, password);
    }
}
