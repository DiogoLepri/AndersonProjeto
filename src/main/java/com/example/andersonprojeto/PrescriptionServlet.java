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
        String cpf = request.getParameter("cpf");
        String borndate = request.getParameter("borndate");
        String healthinsurance = request.getParameter("healthinsurance");
        String doctorname = request.getParameter("doctorname");
        String crm = request.getParameter("CRM");
        String medicinename = request.getParameter("medicinename");
        String dosage = request.getParameter("dosage");
        String amount = request.getParameter("amount");
        String admin = request.getParameter("Admin");
        String obs = request.getParameter("obs");

        if (name != null && !name.isEmpty() && description != null && !description.isEmpty()) {
            String prescription = "Paciente: " + name + " (CPF: " + cpf + ", Nasc: " + borndate + ", Plano: " + healthinsurance + ")\n" +
                    "Médico: " + doctorname + " (CRM: " + crm + ")\n" +
                    "Medicamento: " + medicinename + " (Dosagem: " + dosage + ", Quantidade: " + amount + ", Admin: " + admin + ")\n" +
                    "Descrição: " + description + "\n" +
                    "Observações: " + obs;
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
