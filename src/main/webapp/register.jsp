<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Management System</title>
    <link rel="stylesheet" type="text/css" href="Register.css">
    <link rel="icon" type="image/png" href="favicon.png">
</head>
<body>
<div class="split-screen">
    <div class="left">
        <h1>BALA. Welcome.</h1>
        <p>Start your journey now with our management system!</p>
    </div>
    <div class="right">
        <form action="/register" method="post">
            <label for="crm">CRM Number</label>
            <input type="text" id="crm" name="crm" required>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Create account</button>
        <p>Already Have An Account? <a href="doctor-login.jsp">Log In</a></p>
        </form>
    </div>
</div>
</body>
</html>

