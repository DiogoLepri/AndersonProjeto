<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="icon" type="image/png"  href="farmed_icone.png">
</head>
<body>
<header>
<div class="Titulo">
    <img src="farmed_logo_nova.png" alt="image/png"/>
    <br>
    <h3 id="SubTitulo">Por favor, selecione uma das opcoes:</h3>
</div>
</header>
<div class="container">
    <div class="half doctor" onclick="window.location.href='doctor-login.jsp';">
        <div class="content">
            <h1>Medico Login</h1>
        </div>
    </div>
    <div class="half patient" onclick="window.location.href='farmaceutico-login.jsp';">
        <div class="content">
            <h1>Login Farmaceutico</h1>
        </div>
    </div>
</div>
</body>
</html>
