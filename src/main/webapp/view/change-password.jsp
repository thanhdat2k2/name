<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/16/2024
  Time: 1:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>reset password</title>
    <link rel="stylesheet" href="./assets/styles.css" />
    <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/css/bootstrap.min.css" />
    <script src="./assets/bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer"
    />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
</head>

<body style="background-color: #ccc">
<nav class="navbar navbar-light" style="background-color: beige">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <a class="navbar-brand" href="#">
            <i class="fa-brands fa-dev me-3" style="font-size: 3rem"></i>
        </a>
        <div class="text-center">
            <h3 class="fw-bold">IMS Recruitment</h3>
        </div>
        <div></div>
    </div>
</nav>
<div>
    <div class="container">
        <div class="m-5">
            <div class="title pb-3 text-center">
                <h4>Reset Password</h4>
                <p>Please set your new password</p>
            </div>
            <form action="ChangePass-servlet" method="post">
                <div class="mb-3 row">
                    <label for="inputPassword" class="col-sm-3 col-form-label text-end">Password</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="inputPassword" name="password">
              </div>
            </div>
            <div class="mb-3 row">
                        <label
                                for="confirmPassword"
                                class="col-sm-3 col-form-label text-end"
                        >Confirm Password</label
                        >
                        <div class="col-sm-6">
                            <input
                                    type="password"
                                    class="form-control"
                                    id="confirmPassword"
                                    name="rePassword"
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4"></div>
                        <div class="col-2">
                            <button type="submit"
                                    class="btn ps-4 pe-4 pt-0 pb-0"
                                    style="background-color: rgb(163, 124, 124)"
                            >
                                Submit
                            </button>
                        </div>
                        <div class="col-6">
                            <button type="reset"
                                    class="btn ps-4 pe-4 pt-0 pb-0"
                                    style="background-color: rgb(163, 124, 124)"
                            >
                                Reset
                            </button>
                        </div>
                    </div>
                <c:if test="${not empty mess}">
                    <div style="display: flex; justify-content: center;">
                        <div style="font-size: 2rem; background-color: #F5F5DC; color: red; border: 2px solid transparent; margin: 1rem 1rem 1rem 1rem; border-radius: .25rem; display: flex; justify-content: center; align-items: center; padding-left: 12px">
                            <span style="text-align: center;">${mess}</span>
                        </div>
                    </div>
                </c:if>
            </form>
        </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>