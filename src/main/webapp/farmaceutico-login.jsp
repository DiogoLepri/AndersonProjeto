<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="stylesheet" type="text/css" href="Register.css">
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@600&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="farmed_icone.png">
</head>
<body>
<div class="split-screen">
    <div class="left">
        <nav id="home"><a href="index.jsp"><u>Home</u></a></nav>
        <h1>Bem-vindo</h1>
        <img src="farmed_logo_nova.png">
    </div>
    <div class="right">
        <div class="content-wrapper">
            <h2>Login Farmácia</h2>
            <form action="loginPh" method="post" class="login-form">
                <label for="codigofarmacia">Código da Farmácia:</label>
                <input type="text" id="codigofarmacia" name="codigofarmacia" readonly>
                <div class="pharmacy-buttons">
                    <button type="button" class="pharmacy-button" onclick="setPharmacyCode(1190)">Panvel</button>
                    <button type="button" class="pharmacy-button" onclick="setPharmacyCode(2231)">Nissei</button>
                </div>
                <label for="password">Senha:</label>
                <input type="password" id="password" name="password" placeholder="Digite sua senha" required>
                <button type="submit">Login</button>
                <% String errorMessage = request.getParameter("error");
                    if (errorMessage != null && errorMessage.equals("true")) { %>
                <p class="error-message">Código da Farmácia ou Senha inválidos.</p>
                <% } %>
                <p>Não tem uma conta? <a href="farmaceutico-register.jsp">Registrar</a></p>
            </form>
        </div>
    </div>
</div>

<script>
    function setPharmacyCode(code) {
        document.getElementById('codigofarmacia').value = code;
    }
</script>
</body>
</html>
