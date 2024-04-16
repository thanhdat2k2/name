<%--
  Created by IntelliJ IDEA.
  candidateDTO: ADMIN
  Date: 4/14/2024
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.dto.CandidateDTO" %>
<%@ page import="model.dto.CandidateSkillDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Candidate Detail</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./assets/css/candidateDTODetail.css">
    <link rel="stylesheet" href="./assets/css/navigation.css">
</head>
<%
    CandidateDTO candidateDTO = (CandidateDTO) request.getAttribute("candidate");
    List<CandidateSkillDTO> candidateSkillDTO = (List<CandidateSkillDTO>) request.getAttribute("candidateSkill");
%>
<body>
<%--<%@include file="navigation.jsp" %>--%>
<div class="home_content">
    <div class="add_interview_content">
        <div class="container mt-2">
            <h2>Candidate</h2>
            <p style="font-size: 21px; margin-top: 30px;"><i style="text-decoration: underline;">Candidate List</i> <i>> Candidate Details</i> </p>
            <table>
                <tr>
                    <th>Full Name</th>
                    <td><%=candidateDTO.getCandidateName()%></td>
                </tr>
                <tr>
                    <th>D.O.B</th>
                    <td><%=candidateDTO.getDob()%></td>
                </tr>
                <tr>
                    <th>Phone number</th>
                    <td><%=candidateDTO.getPhone()%></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td> <%= candidateDTO.getEmail() %></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><%= candidateDTO.getAddress() %></td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td><%= candidateDTO.getGenderName()%></td>
                </tr>
                <tr>
                    <th>CV Attachment</th>
                    <td><%=candidateDTO.getCvAtt()%></td>
                </tr>
                <tr>
                    <th>Current Position</th>
                    <td><%=candidateDTO.getPositionName()%></td>
                </tr>
                <tr>
                    <th>Skills</th>
                    <%
                        for(CandidateSkillDTO c: candidateSkillDTO) {
                    %>
                    <td><%= c.getSkill_name()%></td>
                    <%
                        }
                    %>
                </tr>
                <tr>
                    <th>Recruiter</th>
                    <td><%= candidateDTO.getRecruiterName() %></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td> <%= candidateDTO.getCStatusName() %></td>
                </tr>
                <tr>
                    <th>Year of Experience</th>
                    <td><%= candidateDTO.getYoe() %></td>
                </tr>
                <tr>
                    <th>Highest Level</th>
                    <td><%= candidateDTO.getLevelName()%></td>
                </tr>
                <tr>
                    <th>Note</th>
                    <td><%= candidateDTO.getNote()%></td>
                </tr>
            </table>
            <a class="back-button" href="/ims_ee_project_war/ListCandidate">Back to List</a>
        </div>
    </div>
</div>
</body>
</html>
