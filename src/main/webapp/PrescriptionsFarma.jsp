<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: diogo
  Date: 29/05/2024
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="icon" type="image/png"  href="farmed_icone.png">
    <link rel="stylesheet" href="farmaciainterface.css">
</head>
<body>
<header>
    <h1>Acesso a prescricao de farmaceutico</h1>
</header>
<main>
    <section class="search-section">
        <input type="text" id="search-input" placeholder="Pesquisar prescricoes">
        <button id="search-button">Pesquisar</button>
    </section>
    <section class="prescription-list">
        <%
            String pharmacyCode = (String) request.getAttribute("pharmacyCode");
            List<String> prescriptions = (List<String>) request.getAttribute("prescriptions");
            if (prescriptions != null && !prescriptions.isEmpty()) {
        %>
        <table>
            <thead>
            <tr>
                <th>Nome do paciente</th>
                <th>Medico</th>
                <th>Prescricao</th>
                <th>Data</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (String prescription : prescriptions) {
                    String[] prescriptionData = prescription.split("\n");
                    String patientInfo = prescriptionData[0];
                    String doctorInfo = prescriptionData[1];
                    String medicineInfo = prescriptionData[2];
                    String description = prescriptionData[3];
                    String observations = prescriptionData[4];
            %>
            <tr>
                <td><%= patientInfo %></td>
                <td><%= doctorInfo %></td>
                <td>
                    <%= medicineInfo %><br>
                    <%= description %><br>
                    <%= observations %>
                </td>
                <td><!-- Add date here --></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p>No prescriptions found for Pharmacy Code: <%= pharmacyCode %></p>
        <% } %>
    </section>
</main>
</body>
</html>
