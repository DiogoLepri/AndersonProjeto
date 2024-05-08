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

        // Debugging output
        System.out.println("Attempting login with CRM: " + crm);

        if (authenticateUser(crm, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", crm);
            System.out.println("Login successful for CRM: " + crm);
            response.sendRedirect("prescriptions.jsp");
        } else {
            System.out.println("Login failed for CRM: " + crm);
            response.sendRedirect("doctor-login.jsp?error=true");  // Redirecting with an error parameter
        }
    }

    private boolean authenticateUser(String crm, String password) {
        // Implementation should validate the CRM and password against stored data
        // This is just a placeholder for the authentication logic
        return UserStorage.getInstance().authenticateUser(crm, password);
    }
}
