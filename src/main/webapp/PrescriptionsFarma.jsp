<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.andersonprojeto.Prescription" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="icon" type="image/png" href="farmed_icone.png">
    <link rel="stylesheet" href="farmaciainterface.css">
</head>
<body>
<header>
    <nav id="home"><a href="index.jsp"><u>Home</u></a></nav>
    <h1>Acesso a prescrição de farmacêutico</h1>
</header>
<main>
    <section class="search-section">
        <input type="text" id="search-input" placeholder="Pesquisar prescrições">
        <button id="search-button">Pesquisar</button>
    </section>
    <section class="prescription-list">
        <%
            List<Prescription> prescriptions = (List<Prescription>) request.getAttribute("prescriptions");
            if (prescriptions != null && !prescriptions.isEmpty()) {
        %>
        <table>
            <thead>
            <tr>
                <th>Nome do paciente</th>
                <th>Médico</th>
                <th>Prescrição</th>
                <th>Data</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Prescription prescription : prescriptions) {
            %>
            <tr>
                <td><%= prescription.getPatientName() %></td>
                <td><%= prescription.getDoctorName() %></td>
                <td>
                    <strong>Medicamento:</strong> <%= prescription.getMedicineName() %><br>
                    <strong>Descrição:</strong> <%= prescription.getDescription() %><br>
                    <strong>Dosagem:</strong> <%= prescription.getDosage() %><br>
                    <strong>Quantidade:</strong> <%= prescription.getAmount() %><br>
                    <strong>Administração:</strong> <%= prescription.getAdmin() %><br>
                    <strong>Observações:</strong> <%= prescription.getObs() %>
                </td>
                <td><%= prescription.getCreatedAt() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p>Nenhuma prescrição encontrada.</p>
        <% } %>
    </section>
</main>
</body>
</html>
