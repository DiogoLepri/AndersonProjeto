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

        if (!userStorage.addUser(crm, password)) {
            request.setAttribute("errorMessage", "User already exists. Try a different CRM.");
            request.getRequestDispatcher("/register.jsp").forward(request, response); // Just "/register.jsp" if it's at the root
        } else {
            response.sendRedirect("doctor-login.jsp"); // No need to prepend context path if redirecting to a resource in the same context
        }

    }

}

