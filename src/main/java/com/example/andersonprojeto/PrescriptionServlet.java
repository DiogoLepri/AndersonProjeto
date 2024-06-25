package com.example.andersonprojeto;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/prescriptions")
public class PrescriptionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("prescriptionMap", new HashMap<>());
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
        String pharmacyCode = request.getParameter("pharmacyCode");

        // Validação dos campos
        if (name == null || name.isEmpty() ||
                description == null || description.isEmpty() ||
                cpf == null || cpf.isEmpty() ||
                borndate == null || borndate.isEmpty() ||
                healthinsurance == null || healthinsurance.isEmpty() ||
                doctorname == null || doctorname.isEmpty() ||
                crm == null || crm.isEmpty() ||
                medicinename == null || medicinename.isEmpty() ||
                dosage == null || dosage.isEmpty() ||
                amount == null || amount.isEmpty() ||
                admin == null || admin.isEmpty() ||
                obs == null || obs.isEmpty() ||
                pharmacyCode == null || pharmacyCode.isEmpty()) {
            // Campos obrigatórios não preenchidos, redirecionar de volta para o formulário com uma mensagem de erro
            request.setAttribute("error", "Por favor, preencha todos os campos obrigatórios.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Validação do CPF
        if (!cpf.matches("\\d{11}")) {
            request.setAttribute("error", "CPF inválido. O CPF deve conter 11 dígitos numéricos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Validação do formato da data de nascimento
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(borndate);
        } catch (ParseException e) {
            request.setAttribute("error", "Data de nascimento inválida. O formato deve ser dd/MM/yyyy.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Validação do formato do CRM
        if (!crm.matches("\\d{4,6}")) {
            request.setAttribute("error", "CRM inválido. O CRM deve conter entre 4 e 6 dígitos numéricos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Validação da quantidade
        try {
            int amountValue = Integer.parseInt(amount);
            if (amountValue <= 0) {
                request.setAttribute("error", "Quantidade inválida. A quantidade deve ser um número inteiro positivo.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Quantidade inválida. A quantidade deve ser um número inteiro.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Restante do código para processar a prescrição
        String prescription = "Paciente: " + name + " (CPF: " + cpf + ", Nasc: " + borndate + ", Plano: " + healthinsurance + ")\n" +
                "Médico: " + doctorname + " (CRM: " + crm + ")\n" +
                "Medicamento: " + medicinename + " (Dosagem: " + dosage + ", Quantidade: " + amount + ", Admin: " + admin + ")\n" +
                "Descrição: " + description + "\n" +
                "Observações: " + obs;

        synchronized (getServletContext()) {
            Map<String, List<String>> prescriptionMap = (Map<String, List<String>>) getServletContext().getAttribute("prescriptionMap");
            List<String> pharmacyPrescriptions = prescriptionMap.getOrDefault(pharmacyCode, new ArrayList<>());
            pharmacyPrescriptions.add(prescription);
            prescriptionMap.put(pharmacyCode, pharmacyPrescriptions);
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
            String pharmacyCode = request.getParameter("pharmacyCode");
            synchronized (getServletContext()) {
                Map<String, List<String>> prescriptionMap = (Map<String, List<String>>) getServletContext().getAttribute("prescriptionMap");
                List<String> prescriptions = prescriptionMap.get(pharmacyCode);
                request.setAttribute("pharmacyCode", pharmacyCode);
                request.setAttribute("prescriptions", prescriptions);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");
            dispatcher.forward(request, response);
        }
    }
}