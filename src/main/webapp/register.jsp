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
        <div class="content-wrapper"> <!-- New wrapper for centering content -->
            <h2>Faca Cadastro</h2>
            <form action="register" method="post">
                <label for="crm" style="text-align:start">CRM:</label>
                <input type="text" id="crm" name="crm" placeholder="Digite seu CRM" required>
                <label for="password">Senha:</label>
                <input type="password" id="password" name="password" placeholder="Digite sua senha" required>
                <button type="submit">Criar conta</button>
                <p>Ja tem uma conta? <a href="doctor-login.jsp">Log In</a></p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
