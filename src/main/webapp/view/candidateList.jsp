<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/10/2024
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.CandidateDTO" %>
<%@ page import="model.dto.CStatusDTO" %>

<html>
<head>
    <title>View List Candidate</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./assets/css/userList.css">
</head>
<%
    boolean successMessage = false;
    if(request.getAttribute("successMessage") != null) {
        successMessage = (boolean) request.getAttribute("successMessage");
    }
    if (successMessage) {
        out.println("<script>showSuccessMessage();</script>");
    }
%>
<body>
<%@include file="navigation.jsp"%>
<div class="home_content">
    <div class="container mt-2">
        <h2>Candidate Management</h2>
        <p style="font-size: 21px; margin-top: 30px;"><i>Candidate List</i></p>
        <form action="ListCandidate" method="GET">
        <div class="row">
            <div class="col-3 choose">
                    <input type="text" id="candidate_name" name="candidate_name" placeholder="Search" value = "${candidate_name}">
                    <button type="submit">Search</button>
            </div>
            <div class="col-3 choose">
                <select class="form-select" aria-label="Status" name="status">
                    <option value="-1">All</option>
                    <%
                    List<CStatusDTO> cStatusList = (List<CStatusDTO>) request.getAttribute("cStatusList");
                    for (CStatusDTO cStatus : cStatusList) {
                %>
                    <option value="<%= cStatus.getCStatusId()%>"><%= cStatus.getCStatusName()%></option>
                    <% } %>
                </select>
            </div>
            <div class="col-3">
            </div>
        </div>
        </form>
        <div class="row">
            <div class="col-10">
            </div>
            <div class="col-2 add-btn">
                <a href="CreateCandidate">Add new Candidate</a>
            </div>
        </div>
    </div>
    <table style="margin: 20px; border-spacing: 20px" >
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone No.</th>
            <th>Current Postition</th>
            <th>Owner HR</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            List<CandidateDTO> candidateList = (List<CandidateDTO>) request.getAttribute("candidateList");
            for (CandidateDTO candidateDTO : candidateList) {
        %>
        <tr>
            <td><%= candidateDTO.getCandidateName() %></td>
            <td><%= candidateDTO.getEmail() %></td>
            <td><%= candidateDTO.getPhone() %></td>
            <td><%= candidateDTO.getPositionName() %></td>
            <td><%= candidateDTO.getOwnerHR() %></td>
            <td><%= candidateDTO.getCStatusName() %></td>
            <td style="color: #1f1e25"><a href="/ims_ee_project_war/ViewCandidate?candidateId=<%=candidateDTO.getCandidateId() %>"><i class="bi bi-eye me-2"></i></a> <a href="/ims_ee_project_war/EditCandidate?candidateId=<%= candidateDTO.getCandidateId() %>"><i class="bi bi-pencil-square"></i></a></td>
        </tr>
        <% } %>
    </table>
</div>
<script>
    function showSuccessMessage() {
        alert("User added successfully!");
    }
</script>
</body>
</html>
