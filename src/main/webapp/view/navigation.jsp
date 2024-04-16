<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/10/2024
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navigation</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./assets/css/navigation.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .back-button {
            display: inline-block;
            padding: 8px 16px;
            background-color: #CCCCCC;
            color: #000;
            text-decoration: none;
            border-radius: 4px;
            margin-left: 30px;
        }

    </style>
</head>
<body>
<div class="sidebar">
    <div class="logo_content">
        <div class="logo">
<%--            <img src="/assets/img/dev-black.png" width="28px" height="28px" alt="">--%>
            <i class="fa-brands fa-dev me-3" width="28px" height="28px" style="font-size: 4rem"></i>
            <div class="logo_name"><strong>IMS</strong></div>
        </div>
        <i class="bx bx-menu" id="btn"></i>
    </div>
    <ul class="nav_list">
        <li>
            <a href="index.html">
                <i class="bi bi-house-door"></i>
            </a>
        </li>
        <li>
            <a href="/ims_ee_project_war/ListCandidate">
                <i class="bi bi-people"></i>
                <span class="links_name">Candidate</span>
            </a>
            <span class="des">Candidate</span>
        </li>
        <li>
            <a href="">
                <i class="bi bi-clipboard2-minus"></i>
                <span class="links_name">Job</span>
            </a>
            <span class="des">Job</span>
        </li>
        <li>
            <a href="interviewList.html">
                <i class="bi bi-wechat"></i>
                <span class="links_name">Interview</span>
            </a>
            <span class="des">Interview</span>
        </li>
        <li>
            <a href="">
                <i class="bi bi-receipt"></i>
                <span class="links_name">Offer</span>
            </a>
            <span class="des">Offer</span>
        </li>
        <li>
            <a href="/ims_ee_project_war/viewUser-servlet">
                <i class="bi bi-person-gear"></i>
                <span class="links_name">User</span>
            </a>
            <span class="des">User</span>
        </li>
    </ul>
</div>
<%--<div class="home_content">--%>
<%--    <div class="text">Home page</div>--%>
<%--</div>--%>

<script>
    let btn = document.querySelector("#btn");
    let sidebar = document.querySelector(".sidebar");

    btn.onclick = function() {
        sidebar.classList.toggle("active");
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
