<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="stylesheet" type="text/css" href="Register.css">
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@600&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png"  href="farmed_icone.png">
</head>
<body>
<div class="split-screen">
    <div class="left">
        <nav id="home"><a  href="index.jsp"><u>Home</u></a></nav>
        <h1>Bem-vindo</h1>
        <img src="farmed_logo_nova.png">
    </div>
    <div class="right">
        <div class="content-wrapper"> <!-- New wrapper for centering content -->
            <h2>Realize seu Cadastro</h2>
            <form action="registerPh" method="post">
                <label for="crm" >Codigo Farmacia:</label>
                <input type="text" id="crm" name="crm" placeholder="Digite seu codigo de farmacia" required>
                <label for="password" >Senha:</label>
                <input type="password" id="password" name="password" placeholder="Digite sua senha" required>
                <button type="submit">Criar conta</button>
                <p>Ja tem uma conta? <a href="farmaceutico-login.jsp">Log In</a></p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
