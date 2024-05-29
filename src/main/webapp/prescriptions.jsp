<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Farmed</title>
    <link rel="icon" type="image/png"  href="favicon.png">
    <link rel="stylesheet" type="text/css" href="Prescription.css">
</head>
<body>
<h1>Nova prescricao</h1>
<form action="prescriptions" method="post">
    <h2>Dados do Paciente:</h2>
    <label for="name">Nome do Paciente:</label>
    <input type="text" id="name" name="name"><br>
    <label for="cpf">CPF:</label>
    <input type="text" id="cpf" name="cpf"><br>
    <label for="borndate">Data de nascimento:</label>
    <input type="text" id="borndate" name="borndate"><br>
    <label for="healthinsurance">Plano de saude:</label>
    <input type="text" id="healthinsurance" name="healthinsurance"><br><br>

    <h2>Medico Responsavel:</h2>
    <label for="doctorname">Nome do Medico:</label>
    <input type="text" id="doctorname" name="doctorname"><br>
    <label for="CRM">CRM:</label>
    <input type="text" id="CRM" name="CRM"><br><br>

    <h3>Descricao:</h3>
    <label for="description">Descricao:</label>
    <input type="text" id="description" name="description"><br>
    <label for="medicinename">Nome do Medicamento:</label>
    <input type="text" id="medicinename" name="medicinename"><br>
    <label for="dosage">Dosagem:</label>
    <input type="text" id="dosage" name="dosage"><br>
    <label for="amount">Quantidade:</label>
    <input type="text" id="amount" name="amount"><br>
    <label for="Admin">Via de administracao:</label>
    <input type="text" id="Admin" name="Admin"><br>
    <label for="obs">Observacoes:</label>
    <input type="text" id="obs" name="obs"><br><br>

    <input type="submit" value="Imprimir">
    <input type="submit" value="Enviar para Farmácia">
</form>

<%
    List<String> prescriptions = (List<String>) request.getAttribute("prescriptions");
    if (prescriptions != null && !prescriptions.isEmpty()) {
        out.println("<ul>");
        for (String prescription : prescriptions) {
            out.println("<li>" + prescription + "</li>");
        }
        out.println("</ul>");
    } else {
        out.println("<p>No prescriptions found</p>");
    }
%>
</body>
</html>
