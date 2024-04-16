<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/9/2024
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>login</title>
    <link rel="stylesheet" href="./assets/styles.css"/>
    <link
            rel="stylesheet"
            href="./assets/bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/css/bootstrap.min.css"
    />
    <script src="./assets/bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
            integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">
    <div class="pt-4 pb-4 ps-3 pe-3" style="width: 600px; background-color: beige;">

        <form action="login-servlet" method="POST">
            <div class="mb-3 d-flex align-items-center" style="padding-left: 150px">
                <i class="fa-brands fa-dev me-3" style="font-size: 4rem"></i>
                <h2 style="display: inline; margin: 0 30px 10px 30px">IMS Recruitment</h2>
            </div>
            <div class="mt-3">
                <label for="userName" style="width: 200px">Username</label>
                <input name="username" value="${last_user}" type="text" class="form-control border-0" id="userName"/>
            </div>
            <div class="mt-3">
                <label for="password" style="width: 200px">Password</label>
                <input name="password" value="${last_pass}" type="password" class="form-control border-0"
                       id="password"/>
            </div>
            <div class="mt-3 mb-3 d-flex justify-content-between">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="rememberMeCheckbox"/>
                    <label class="form-check-label">Remember me?</label>
                </div>
                <div class="mt-3">
                    <a href="ForgotPassword-servlet">Forgot password?</a>
                </div>
            </div>
            <div class="d-flex justify-content-center mt-3">
                <button type="submit" class="btn ps-5 pe-5" style=" background-color:#CCC">Login</button>
            </div>

        </form>
        <c:if test="${not empty success}">
            <div style="font-size: 2rem; background-color: #F5F5DC; color: red; border: 2px solid transparent; margin: 1rem 1rem 1rem 1rem; border-radius: .25rem; display: flex; justify-content: center; align-items: center; padding-left: 12px">
                <span style="text-align: center;">${success}</span>
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <div style="font-size: 2rem; background-color: #F5F5DC; color: red; border: 2px solid transparent; margin: 1rem 1rem 1rem 1rem; border-radius: .25rem; display: flex; justify-content: center; align-items: center; padding-left: 12px">
                <span style="text-align: center;">${error}</span>
            </div>
        </c:if>
    </div>
</div>
<script>
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (document.getElementById("rememberMeCheckbox").checked) {
        localStorage.setItem("rememberedUsername", username);
        localStorage.setItem("rememberedPassword", password);
    } else {
        localStorage.removeItem("rememberedUsername");
        localStorage.removeItem("rememberedPassword");
    }


    let rememberedUsername = localStorage.getItem("rememberedUsername");
    let rememberedPassword = localStorage.getItem("rememberedPassword");


    if (rememberedUsername && rememberedPassword) {
        document.getElementById("username").value = rememberedUsername;
        document.getElementById("password").value = rememberedPassword;
    }
</script>
</body>
</html>