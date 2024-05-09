<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="stylesheet" type="text/css" href="Register.css">
    <link rel="icon" type="image/png" href="favicon.png">
</head>
<body>
<div class="split-screen">
    <div class="left">
        <h1>Bem-vindo a Farmed</h1>
    </div>
    <div class="right">
        <h2>Faca Login</h2>
        <form action="register" method="post">
            <label for="crm">CRM:</label>
            <input type="text" id="crm" name="crm" placeholder="Digite seu CRM" required>
            <label for="password">Senha:</label>
            <input type="password" id="password" name="password" placeholder="Digite sua senha" required>
            <button type="submit">Criar conta</button>
        <p>Ja tem uma conta? <a href="doctor-login.jsp">Log In</a></p>
        </form>
    </div>
</div>
</body>
</html>

