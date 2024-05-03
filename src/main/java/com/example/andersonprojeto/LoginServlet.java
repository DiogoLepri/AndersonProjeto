package com.example.andersonprojeto;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crm = request.getParameter("crm");
        String password = request.getParameter("password");

        boolean isAuthenticated = authenticateUser(crm, password);

        if (isAuthenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("user", crm);
            System.out.println("Login successful for CRM: " + crm);
            response.sendRedirect("prescriptions.jsp");
        } else {
            System.out.println("Login failed for CRM: " + crm);
            response.sendRedirect("login.jsp");
        }
    }

    private boolean authenticateUser(String crm, String password) {
        return UserStorage.getInstance().authenticateUser(crm, password);
    }
}
