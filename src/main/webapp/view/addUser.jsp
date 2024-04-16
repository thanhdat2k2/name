<%@ page import="model.Gender" %>
<%@ page import="dao.GenderDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="model.Role" %>
<%@ page import="dao.DepartmentDAO" %>
<%@ page import="model.Department" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/11/2024
  Time: 12:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new User</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./assets/css/addNewUser.css">
</head>
<%
    GenderDAO genderDAO = new GenderDAO();
    List<Gender> genderList = genderDAO.getAllGender();
    int genderId = 0;
    if(request.getParameter("genderId") != null){
        genderId = Integer.parseInt(request.getParameter("genderId"));
    }

    RoleDAO roleDAO = new RoleDAO();
    List<Role> roleList = roleDAO.getAllRole();
    int roleId = 0;
    if(request.getParameter("roleId") != null){
        roleId = Integer.parseInt(request.getParameter("roleId"));
    }

    DepartmentDAO departmentDAO = new DepartmentDAO();
    List<Department> departmentList = departmentDAO.getAllDepartment();
    int departmentId = 0;
    if(request.getParameter("departmentId") != null){
        departmentId = Integer.parseInt(request.getParameter("departmentId"));
    }

    String error = request.getParameter("error");
%>
<body>
<%@include file="navigation.jsp"%>
<div class="home_content">
    <div class="add_interview_content">
    <div class="container mt-2">
        <h2>User</h2>
        <p style="font-size: 21px; margin-top: 30px;"><i style="text-decoration: underline;">User List</i> <i>> Create user</i> </p>
        <form action="addUser-servlet" method="post" class="form-interview">
            <div class="form-row">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input name="username" type="text" id="username" placeholder="Type a username..." value="${username}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input name="password" type="password" id="password" placeholder="Type an password..." value="${password}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="rePassword">Re-Password</label>
                    <input name="rePassword" type="password" id="rePassword" placeholder="Type an Re-password..." value="${rePassword}">
                </div>
                <div class="form-group">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="fullName">Full name</label>
                    <input name="fullName" type="text" id="fullName" placeholder="Type a name..." value="${fullName}">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input name="email" type="text" id="email" placeholder="Type an email..." value="${email}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>D.O.B</label>
                    <input name="date" type="date"  placeholder="DD/MM/YYYY" value="${dob}">
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input name="address" type="text" id="address" placeholder="Type a address..." value="${address}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="phoneNumber">Phone number</label>
                    <input name="phoneNumber" type="text" id="phoneNumber" placeholder="Type a number..." value="${phoneNumber}">
                </div>
                <div class="form-group">
                    <label>Gender</label>
                    <select class="form-select" aria-label="gender" name="genderId">
                        <option selected disabled hidden>Select a gender</option>
                        <% for (Gender d : genderList) { %>
                        <option value="<%= d.getGenderId() %>"
                                <%=genderId==d.getGenderId()?"selected":""%>>
                            <%=d.getGenderName()%>
                        </option>
                        <% } %>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Roles</label>
                    <select class="form-select" aria-label="roles" name="roleId">
                        <option selected disabled hidden>Roles</option>
                        <% for (Role d : roleList) { %>
                        <option value="<%= d.getRoleId() %>"
                                <%=roleId==d.getRoleId()?"selected":""%>>
                            <%=d.getRoleName()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Department</label>
                    <select class="form-select" aria-label="department" name="departmentId">
                        <option selected disabled hidden>Department</option>
                        <% for (Department d : departmentList) { %>
                        <option value="<%= d.getDepartmentId() %>"
                                <%=departmentId==d.getDepartmentId()?"selected":""%>>
                            <%=d.getDepartmentName()%>
                        </option>
                        <% } %>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Status</label>
                    <select class="form-select" aria-label="status" name="status">
                        <option selected disabled hidden>Status</option>
                        <option value="1" selected>Activate</option>
                        <option value="2">Deactivate</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="note">Note</label>
                    <input name="note" type="text" id="note" placeholder="N/A" value="${note}">
                </div>
            </div>
            <div class="form-row btn">
                <button type="submit">Submit</button>
                <a class="back-button" href="/ims_ee_project_war_exploded/viewUser-servlet">Back to List</a>
            </div>
            <% if (error != null) { %>
            <p style="color: red;">Error massage:
                <%
                    switch (error) {
                        case "missing_info1":
                            out.print("Please complete all information.");
                            break;
                        case "missing_info2":
                            out.print("Please choose Gender.");
                            break;
                        case "missing_info3":
                            out.print("Please choose Role.");
                            break;
                        case "missing_info4":
                            out.print("Please choose Department.");
                            break;
                        case "missing_info5":
                            out.print("Please choose Status.");
                            break;
                        case "usernameError":
                            out.print("Username must be 30 characters or less.");
                            break;
                        case "phoneError":
                            out.print("Phone must be 10 digits.");
                            break;
                        case "emailError":
                            out.print("Email must be 50 characters or less.");
                            break;
                        case "invalidFormatPhone":
                            out.print("Invalid phone number. Must start with 0 and have 10 digits.");
                            break;
                        case "phoneExist":
                            out.print("Phone number already exists");
                            break;
                        case "invalidFormatEmail":
                            out.print("Invalid email. Must have the extension @gmail.com and must be at least 6 characters .");
                            break;
                        case "emailExist":
                            out.print("Email already exists");
                            break;
                        case "rePassWordError":
                            out.print("Repeat password does not match the password.");
                            break;

                    }
                %>
            </p>
            <% } %>
        </form>
    </div>
    </div>
</div>
</body>

</html>
