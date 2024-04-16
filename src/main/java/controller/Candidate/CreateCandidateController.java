package controller.Candidate;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Gender;
import model.dto.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CreateCandidateController", urlPatterns = "/CreateCandidate")
public class CreateCandidateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        GenderDAO genderDAO = new GenderDAO();
        try {
            List<Gender> genderList = genderDAO.getAllGender();
            req.setAttribute("genderList", genderList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PositionDAO positionDAO = new PositionDAO();
        try {
            List<PositionDTO> positionList = positionDAO.getAllPosition();
            req.setAttribute("positionList", positionList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CStatusDAO cStatusDAO = new CStatusDAO();
        try {
            List<CStatusDTO> cStatusList = cStatusDAO.getListcStatus();
            req.setAttribute("cStatusList", cStatusList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SkillDAO skillDAO = new SkillDAO();
        try {
            List<SkillDTO> skillList = skillDAO.getAllSKill();
            req.setAttribute("skillList", skillList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = new UserDAO();
        try {
            List<UserDTO> userList = userDAO.getUserIdAndName();
            req.setAttribute("userList", userList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LevelDAO levelDAO = new LevelDAO();
        try {
            List<LevelDTO> levelList = levelDAO.getAllLevel();
            req.setAttribute("levelList", levelList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("./view/addCandidate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String dobStr = req.getParameter("dob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int genderId = Integer.parseInt(req.getParameter("genderId"));
        String cv_att = req.getParameter("cv_att");
        String note = req.getParameter("note");
        int positionId = Integer.parseInt(req.getParameter("positionId"));
        int status = Integer.parseInt(req.getParameter("status"));
        int yoe = Integer.parseInt(req.getParameter("yoe"));
        int recruiterId = Integer.parseInt(req.getParameter("recruiterId"));
        int levelId = Integer.parseInt(req.getParameter("levelId"));
        String ownerHR = req.getParameter("ownerHR");
        String[] skillIdsString = req.getParameterValues("skill_ids");
        // Initialize a list to store integers
        List<Integer> skillIds = new ArrayList<>();
        if (skillIdsString != null && skillIdsString.length > 0) {
            try {
                for (String id : skillIdsString) {
                    skillIds.add(Integer.parseInt(id));
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid skill ID format");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No skill IDs provided");
        }
        CandidateDAO candidateDAO = new CandidateDAO();
        try {
            int candidate_id = candidateDAO.createCandidate(name, dob, phone, email, address, genderId, cv_att, note,
                    positionId, status, yoe, recruiterId, levelId, ownerHR);
            candidateDAO.createCandidateSkill(candidate_id, skillIds);
            List<CandidateDTO> listAllCandidate = candidateDAO.getListCandidate();
            req.setAttribute("candidateList", listAllCandidate);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.html");
        }
    }
}
