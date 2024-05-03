package com.example.andersonprojeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/prescriptions")
public class PrescriptionServlet extends HttpServlet {

    private List<String> prescriptions;

    @Override
    public void init() throws ServletException {
        prescriptions = new ArrayList<>();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        if (name != null && !name.isEmpty() && description != null && !description.isEmpty()) {
            String prescription = name + ": " + description;
            prescriptions.add(prescription);
        }
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String user = (session != null) ? (String) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("prescriptions", prescriptions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
        }
    }
}
