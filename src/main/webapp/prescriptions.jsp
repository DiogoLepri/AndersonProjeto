<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prescrição</title>
    <link rel="icon" type="image/icon" href="logo.png">
</head>
<body>
<h1>Sua Prescrições</h1>
<form action="prescriptions" method="post">
    <label for="name">Nome Prescrição:</label>
    <input type="text" id="name" name="name"><br><br>

    <label for="description">Descrição:</label>
    <input type="text" id="description" name="description"><br><br>

    <input type="submit" value="Add Prescription">
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
