        <%@ page import="dao.UserDAO" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/14/2024
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Position" %>
<%@ page import="dao.PositionDAO" %>
<%@ page import="dao.AccountDAO" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="model.Role" %>
<html>
<head>
    <title>User Detail</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./assets/css/userDetail.css">
    <link rel="stylesheet" href="./assets/css/navigation.css">
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
            width: 200px;
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
            margin-top: 20px;
        }

    </style>
</head>
<%
    PositionDAO positionDAO = new PositionDAO();
    AccountDAO accountDAO = new AccountDAO();
    RoleDAO roleDAO = new RoleDAO();
    String userIdStr = request.getParameter("userId");
    User user = null;
    if (userIdStr != null) {
        int userId = Integer.parseInt(userIdStr);
        UserDAO userDAO = new UserDAO();

        user = userDAO.getUserById(userId);
    }
    int accountId = user.getAccountId();
    int accountRoleId = accountDAO.getRoleIdByAccount(accountId);
    Role role = roleDAO.getRoleById(accountRoleId);
    String roleName = "";
    if (role != null) {
        roleName = role.getRoleName();
    }
    int accountStatus = accountDAO.getAccountStatusById(accountId);
    String status = "";
    if (accountStatus == 1) {
        status = "Activate";
    } else if (accountStatus == 0) {
        status = "Deactivated";
    }
%>
<body>
<%--<%@include file="navigation.jsp" %>--%>
<div class="home_content">
    <div class="add_interview_content">
        <div class="container mt-2">
            <h2>User</h2>
            <p style="font-size: 21px; margin-top: 30px;"><i style="text-decoration: underline;">User List</i> <i>> User Details</i> </p>
            <table>
                <tr>
                    <th>Full Name</th>
                    <td><%=user.getFullName()%></td>
                </tr>
                <tr>
                    <th>D.O.B</th>
                    <td><%=user.getDoB()%></td>
                </tr>
                <tr>
                    <th>Phone number</th>
                    <td><%=user.getPhoneNumber()%></td>
                </tr>
                <tr>
                    <th>Role</th>
                    <td><%= roleName %></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><%= status %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= user.getEmail() %></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><%= user.getAddress() %></td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td> <%= user.getGenderId() %></td>
                </tr>
                <tr>
                    <th>Department</th>
                    <td><%= user.getDepartmentId() %></td>
                </tr>
                <tr>
                    <th>note</th>
                    <td><%= user.getNote() %></td>
                </tr>
            </table>
            <a class="back-button" href="/ims_ee_project_war_exploded/viewUser-servlet">Back to List</a>
        </div>
    </div>
</div>
</body>
</html>
