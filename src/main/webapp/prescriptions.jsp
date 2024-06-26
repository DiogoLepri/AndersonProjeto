<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Farmed</title>
    <link rel="icon" type="image/png"  href="farmed_icone.png">
    <link rel="stylesheet" type="text/css" href="Prescription.css">
</head>
<body>
<h1>Nova prescricao</h1>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
        out.println("<p style='color: red;'>" + error + "</p>");
    }
%>
<nav id="home"><a href="index.jsp"><u>Home</u></a></nav>
<form action="prescriptions" method="post">
    <h2>Dados do Paciente:</h2>
    <label for="name">Nome do Paciente:</label>
    <input type="text" id="name" name="name" value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" placeholder="Digite o nome completo do paciente"><br>
    <label for="cpf">CPF:</label>
    <input type="text" id="cpf" name="cpf" value="<%= request.getAttribute("cpf") != null ? request.getAttribute("cpf") : "" %>" placeholder="Digite o CPF (somente números)"><br>
    <label for="borndate">Data de nascimento:</label>
    <input type="text" id="borndate" name="borndate" value="<%= request.getAttribute("borndate") != null ? request.getAttribute("borndate") : "" %>" placeholder="Digite a data de nascimento (dd/mm/aaaa)"><br>
    <label for="healthinsurance">Plano de saude:</label>
    <input type="text" id="healthinsurance" name="healthinsurance" value="<%= request.getAttribute("healthinsurance") != null ? request.getAttribute("healthinsurance") : "" %>" placeholder="Digite o nome do plano de saúde"><br><br>

    <h2>Medico Responsavel:</h2>
    <label for="doctorname">Nome do Medico:</label>
    <input type="text" id="doctorname" name="doctorname" value="<%= request.getAttribute("doctorname") != null ? request.getAttribute("doctorname") : "" %>" placeholder="Digite o nome completo do médico"><br>
    <label for="CRM">CRM:</label>
    <input type="text" id="CRM" name="CRM" value="<%= request.getAttribute("CRM") != null ? request.getAttribute("CRM") : "" %>" placeholder="Digite o CRM (4 a 6 dígitos)"><br><br>

    <h3>Descricao:</h3>
    <label for="description">Descricao:</label>
    <input type="text" id="description" name="description" value="<%= request.getAttribute("description") != null ? request.getAttribute("description") : "" %>" placeholder="Digite a descrição da prescrição"><br>
    <label for="medicinename">Nome do Medicamento:</label>
    <input type="text" id="medicinename" name="medicinename" value="<%= request.getAttribute("medicinename") != null ? request.getAttribute("medicinename") : "" %>" placeholder="Digite o nome do medicamento"><br>
    <label for="dosage">Dosagem:</label>
    <input type="text" id="dosage" name="dosage" value="<%= request.getAttribute("dosage") != null ? request.getAttribute("dosage") : "" %>" placeholder="Digite a dosagem do medicamento"><br>
    <label for="amount">Quantidade:</label>
    <input type="text" id="amount" name="amount" value="<%= request.getAttribute("amount") != null ? request.getAttribute("amount") : "" %>" placeholder="Digite a quantidade (número inteiro)"><br>
    <label for="Admin">Via de administracao:</label>
    <input type="text" id="Admin" name="Admin" value="<%= request.getAttribute("Admin") != null ? request.getAttribute("Admin") : "" %>" placeholder="Digite a via de administração"><br>
    <label for="obs">Observacoes:</label>
    <input type="text" id="obs" name="obs" value="<%= request.getAttribute("obs") != null ? request.getAttribute("obs") : "" %>" placeholder="Digite as observações (opcional)"><br><br>

    <!-- Add buttons to select the pharmacy and hidden field to store pharmacy ID -->
    <input type="hidden" id="pharmacyId" name="pharmacyId">
    <button type="button" onclick="setPharmacyId(1190)">Farmácia 1</button>
    <button type="button" onclick="setPharmacyId(2231)">Farmácia 2</button>
    <button type="submit">Enviar Prescrição</button>
</form>

<%
    String pharmacyCode = request.getParameter("pharmacyCode");
    if (pharmacyCode != null && !pharmacyCode.isEmpty()) {
        List<String> prescriptions = (List<String>) request.getAttribute("prescriptions");
        if (prescriptions != null && !prescriptions.isEmpty()) {
            out.println("<h2>Prescriptions for Pharmacy Code: " + pharmacyCode + "</h2>");
            out.println("<ul>");
            for (String prescription : prescriptions) {
                out.println("<li>" + prescription + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No prescriptions found for Pharmacy Code: " + pharmacyCode + "</p>");
        }
    }
%>

<script>
    function setPharmacyId(id) {
        document.getElementById('pharmacyId').value = id;
    }
</script>
</body>
</html>
