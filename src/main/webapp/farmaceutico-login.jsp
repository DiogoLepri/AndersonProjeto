<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="icon" type="image/png"  href="farmed_icone.png" >
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="farmaceutico-login.css">
</head>
<body>
<div class="split-screen">
    <div class="left">
        <nav id="home"><a href="index.jsp"><u>Home</u></a></nav>
        <h1>Bem-vindo</h1>
        <img src="farmed_logo_nova.png">
    </div>
    <div class="right">
        <div class="login-wrapper">
            <div class="login-box">
                <h2>Faca Login</h2>
                <form action="loginPh" method="post" class="login-form">
                    <div class="input-group">
                        <label for="cpf">Codigo Farmacia:</label>
                        <input type="text" id="cpf" name="cpf" placeholder="Digite seu cpf" required>
                    </div>
                    <div class="input-group">
                        <label for="password">Senha:</label>
                        <input type="password" id="password" name="password" placeholder="Digite sua senha" required>
                    </div>
                    <button type="submit" class="login-button">Login</button>
                    <% if ("true".equals(request.getParameter("error"))) { %>
                    <p class="error-message" aria-live="polite">CPF ou senha invalida. Por favor tente novamente.</p>
                    <% } %>
                    <p class="signup-text">Nao tem uma conta? <a href="farmaceutico-register.jsp">Sign Up</a></p>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>
