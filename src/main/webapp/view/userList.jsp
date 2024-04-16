<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/10/2024
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Position" %>
<%@ page import="dao.PositionDAO" %>
<%@ page import="dao.AccountDAO" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="model.Role" %>
<%
    PositionDAO positionDAO = new PositionDAO();
    AccountDAO accountDAO = new AccountDAO();
    RoleDAO roleDAO = new RoleDAO();
%>

<html>
<head>
    <title>View List User</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./assets/css/userList.css">
</head>
<%
    boolean successMessage = false;
    if (request.getAttribute("successMessage") != null) {
        successMessage = (boolean) request.getAttribute("successMessage");
    }
    if (successMessage) {
        out.println("<script>showSuccessMessage();</script>");
    }
%>
<body>
<%@include file="navigation.jsp" %>
<div class="home_content">
    <div class="container mt-2">
        <div class="row">
            <div class="col-8">

            </div>
            <div class="col-2">
                <c:set var="role" value="${sessionScope.acc.roleId}" />
                <c:choose>
                    <c:when test="${role == 1}">
                        Welcome  ${sessionScope.admin.fullName}
                    </c:when>
                </c:choose>
            </div>
            <div class="col-1">
                <a href="ChangePass-servlet">Change Pass</a>
            </div>
            <div class="col-1">
                <a href="logout-servlet">Logout</a>
            </div>
        </div>
        <h2>User Management</h2>
        <p style="font-size: 21px; margin-top: 30px;"><i>User List</i></p>
        <div class="row">
            <div class="col-3 choose">
                <form action="searchUser-servlet" method="POST">
                    <input type="text" id="search" name="search" placeholder="Search">
                    <button type="submit" name="searchButton">Search</button>
                    <input type="hidden" id="role" name="role" value="">
                </form>
            </div>
            <div class="col-3 choose">
                <form action="searchUser-servlet" method="POST">
                    <select class="form-select" name="role">
                        <option selected>Select a Role</option>
                        <option value="1">Admin</option>
                        <option value="2">Recruiter</option>
                        <option value="3">Interviewer</option>
                        <option value="4">Manager</option>
                    </select>
                    <button type="submit" name="roleButton">Filter</button>
                </form>
            </div>
            <div class="col-3">
            </div>
        </div>
        <div class="row">
            <div class="col-10">
            </div>
            <div class="col-2 add-btn-new">
                <a class="add-btn-new" href="addUser-servlet">Add new</a>
            </div>
        </div>
    </div>
    <div class="table-container" style="margin-left: 100px">
        <table style="margin: 20px; border-spacing: 20px">
            <tr>
                <th style="width: 180px">Username</th>
                <th style="width: 260px;">Email</th>
                <th style="width: 200px">Phone No.</th>
                <th style="width: 180px">Role</th>
                <th style="width: 180px">Status</th>
                <th>Action</th>
            </tr>
            <%
                List<User> allUser = (List<User>) request.getAttribute("allUser");
                for (User user : allUser) {
                    int positionId = user.getPositionRoles();
                    Position position = positionDAO.getPositionById(positionId);
                    String positionName = "";
                    if (position != null) {
                        positionName = position.getPositionName();
                    }
                    int accountId = user.getAccountId();
                    int accountStatus = accountDAO.getAccountStatusById(accountId);
                    String status = "";
                    if (accountStatus == 1) {
                        status = "Activate";
                    } else if (accountStatus == 0) {
                        status = "Deactivated";
                    }

                    int accountRoleId = accountDAO.getRoleIdByAccount(accountId);
                    Role role = roleDAO.getRoleById(accountRoleId);
                    String roleName = "";
                    if (role != null) {
                        roleName = role.getRoleName();
                    }
            %>
            <tr>
                <td><%= user.getFullName() %>
                </td>
                <td><%= user.getEmail() %>
                </td>
                <td><%= user.getPhoneNumber() %>
                </td>
                <td><%= roleName %>
                </td>
                <td><%= status %>
                </td>
                <td style="color: #1f1e25"><a href="./view/userDetail.jsp?userId=<%= user.getUserId() %>"><i class="bi bi-eye me-2"></i></a> <a href="/ims_ee_project_war_exploded/EditUser-servlet?userId=<%= user.getUserId() %>"><i class="bi bi-pencil-square"></i></a></td>
            </tr>
            <% } %>
        </table>
    </div>

</div>
<script>
    function showSuccessMessage() {
        alert("User added successfully!");
    }
</script>
</body>

</html>
