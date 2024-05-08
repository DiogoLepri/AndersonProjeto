<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmed</title>
    <link rel="stylesheet" href="doctor-login.css">
</head>
<body>
<div class="login-wrapper">
    <div class="login-box">
        <h2>Login to your account</h2>
        <!-- Update the action attribute to the URL of your login servlet -->
        <form action="LoginServlet" method="post">
            <div class="input-group">
                <input type="email" name="email" placeholder="Email" required>
            </div>
            <div class="input-group">
                <input type="password" name="password" placeholder="Password" required>
                <a href="#" class="forgot-link">Forgot?</a>
            </div>
            <button type="submit" class="login-button">Login now</button>
            <p class="signup-text">Nao tem uma conta? <a href="register.jsp">Criar conta:</a></p>
        </form>
    </div>
</div>
</body>
</html>
