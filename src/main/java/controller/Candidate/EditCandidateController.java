package controller.Candidate;

import dao.CandidateDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.CandidateDTO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EditCandidateController", urlPatterns = "/EditCandidate")
public class EditCandidateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int candidateId = Integer.parseInt(req.getParameter("candidateId"));
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String dobStr = req.getParameter("date");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        int genderId = Integer.parseInt(req.getParameter("genderId"));
        String cv_att = req.getParameter("cv_att");
        String note = req.getParameter("note");
        int positionId = Integer.parseInt(req.getParameter("positionId"));
        int yoe = Integer.parseInt(req.getParameter("yoe"));
        int recruiterId = Integer.parseInt(req.getParameter("recruiterId"));
        int levelId = Integer.parseInt(req.getParameter("levelId"));
        String[] skillIdsString = req.getParameterValues("skill_ids");
        List<Integer> skillIdsList = new ArrayList<>();
        if (skillIdsString != null) {
            for (String skillId : skillIdsString) {
                try {
                    skillIdsList.add(Integer.parseInt(skillId));
                } catch (NumberFormatException e) {
                }
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        CandidateDTO candidateDTO = new CandidateDTO(candidateId, fullName, email, dob, address, phoneNumber, genderId,
                cv_att, positionId, yoe, levelId, recruiterId, note);
        CandidateDAO candidateDAO = new CandidateDAO();
        try {
            int updateCandidateId = candidateDAO.updateCandidate(candidateDTO);
            int skillUpdateCandidateId = candidateDAO.updateSKillForCandidate(updateCandidateId, skillIdsList);
            req.setAttribute("updateCandidateId", updateCandidateId);
            req.setAttribute("skillUpdateCandidateId", skillUpdateCandidateId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
