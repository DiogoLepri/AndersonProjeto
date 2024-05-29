package com.example.andersonprojeto;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/pharmacy")
public class PharmacyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String user = (session != null) ? (String) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("farmaceutico-login.jsp");
        } else {
            synchronized (getServletContext()) {
                List<String> prescriptions = (List<String>) getServletContext().getAttribute("prescriptions");
                request.setAttribute("prescriptions", prescriptions);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("PrescriptionsFarma.jsp");
            dispatcher.forward(request, response);
        }
    }
}
