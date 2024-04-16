<%@ page import="model.Gender" %>
<%@ page import="dao.GenderDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="model.Role" %>
<%@ page import="dao.DepartmentDAO" %>
<%@ page import="model.Department" %>
<%@ page import="model.dto.PositionDTO" %>
<%@ page import="model.dto.CStatusDTO" %>
<%@ page import="model.dto.SkillDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/11/2024
  Time: 12:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Candidate</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./assets/css/addNewUser.css">
</head>
<%
    String error = request.getParameter("error");
%>
<body>
<%@include file="navigation.jsp"%>
<div class="home_content">
    <div class="add_interview_content">
        <div class="container mt-2">
            <h2>Candidate</h2>
            <p style="font-size: 21px; margin-top: 30px;"><i style="text-decoration: underline;">Candidate List</i> <i>> Create Candidate</i> </p>
            <form action="CreateCandidate" method="post" class="form-interview">
                <div class="form-row">
                    <div class="form-group">
                        <label for="name">Full name</label>
                        <input name="name" type="text" id="name" placeholder="Type a name..." value="${name}">
                    </div>
                    <div class="form-group">
                        <label for="email">Password</label>
                        <input name="email" type="text" id="email" placeholder="Type an email..." value="${email}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>D.O.B</label>
                        <input name="date" type="date" placeholder="DD/MM/YYYY" value="${dob}">
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
                            <%
                                List<Gender> genderList = (List<Gender>) request.getAttribute("genderList");
                                for (Gender g : genderList) {
                            %>
                            <option value="<%= g.getGenderId()%>"><%= g.getGenderName()%></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="cvAtt">CV Attachment</label>
                        <input name="cvAtt" type="file" id="cvAtt" value="${cvAtt}">
                    </div>
                    <div class="form-group">
                        <label for="note">Note</label>
                        <input name="note" type="text" id="note" placeholder="N/A" value="${note}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Position</label>
                        <select class="form-select" aria-label="position" name="position">
                            <option selected disabled hidden>Select a position... ex: Backend developer,...</option>
                            <%
                                List<PositionDTO> positionList = (List<PositionDTO>) request.getAttribute("positionList");
                                for (PositionDTO p : positionList) {
                            %>
                            <option value="<%= p.getPositionId()%>"><%= p.getPositionName()%></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <select class="form-select" aria-label="status" name="status">
                            <option selected disabled hidden>Select a status</option>
                            <%
                                List<CStatusDTO> cStatusList = (List<CStatusDTO>) request.getAttribute("cStatusList");
                                for (CStatusDTO cs : cStatusList) {
                            %>
                            <option value="<%= cs.getCStatusId()%>"><%= cs.getCStatusName()%></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Skills</label>
                        <select class="form-select" aria-label="skill" name="skill" multiple>
                            <% List<SkillDTO> skillList = (List<SkillDTO>) request.getAttribute("skillList");
                                List<Integer> selectedSkillIds = new ArrayList<>();
                                if(request.getAttribute("skillIdList") != null){
                                    selectedSkillIds = (List<Integer>)request.getAttribute("skillIdList");
                                }
                                for (SkillDTO skill : skillList) { %>
                            <div class="checkbox-container">
                                <label class="checkbox-label" for="skill<%=skill.getSkillId()%>">
                                    - <%=skill.getSkillName()%>
                                </label>
                                <input type="checkbox" name="selectedSkills" id="service<%=skill.getSkillId()%>" value="<%=skill.getSkillId()%>"
                                    <%=selectedSkillIds.contains(skill.getSkillId())?"checked":""%>>
                            </div>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="yoe">Year of Experience</label>
                        <input name="yoe" type="number" id="yoe" placeholder="Type a number" value="${yoe}">
                    </div>
                </div>
                <div class="form-row btn">
                    <button type="submit">Submit</button>
                    <a class="back-button" href="/ims_ee_project_war/ListCandidate">Back to List</a>
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
