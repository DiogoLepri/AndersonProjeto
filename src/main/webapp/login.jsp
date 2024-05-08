<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
    <link rel="icon" type="image/icon" href="logo.png">
</head>
<body>
<form action="login" method="post">
    <label for="crm">CRM:</label>
    <input type="text" id="crm" name="crm"><br><br>

    <label for="password">Senha:</label>
    <input type="password" id="password" name="password"><br><br>

    <input type="submit" value="Login">
    <ul>
        <li><a href="register.jsp">Registrar</a></li>
    </ul>
</form>
</body>
</html>
