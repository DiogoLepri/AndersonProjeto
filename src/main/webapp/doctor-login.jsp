<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="icon" type="image/png"  href="favicon.png">
    <link rel="stylesheet" href="doctor-login.css">
</head>
<body>
<div class="split-screen">
    <div class="left">
        <h1>Bem-vindo a Farmed</h1>
    </div>
    <div class="right">
        <div class="login-wrapper">
            <div class="login-box">
                <h2>Faca Login</h2>
                <form action="login" method="post">
                    <div class="input-group">
                        <label for="crm">CRM:</label>
                        <input type="text" id="crm" name="crm" placeholder="Digite seu CRM" required>
                    </div>
                    <div class="input-group">
                        <label for="password">Senha:</label>
                        <input type="password" id="password" name="password" placeholder="Digite sua senha" required>
                    </div>
                    <button type="submit" class="login-button">Login</button>
                    <% if ("true".equals(request.getParameter("error"))) { %>
                    <p style="color: red;">CRM ou senha invalido. Por favor tente denovo.</p>
                    <% } %>
                    <p class="signup-text">Nao tem uma conta? <a href="register.jsp">Sign Up</a></p>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
