package com.example.andersonprojeto;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/registerPh")
public class PharmacyRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigofarmacia = request.getParameter("codigofarmacia");
        String password = request.getParameter("password");
        UserStorage userStorage = UserStorage.getInstance();

        if (!userStorage.addUser(codigofarmacia, password)) {
            request.setAttribute("errorMessage", "User already exists. Try a different codigofarmacia.");
            request.getRequestDispatcher("/farmaceutico-register.jsp").forward(request, response); // Just "/register.jsp" if it's at the root
        } else {
            response.sendRedirect("farmaceutico-login.jsp"); // No need to prepend context path if redirecting to a resource in the same context
        }

    }

}

