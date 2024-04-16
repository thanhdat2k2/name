package controller.Candidate;

import dao.CandidateDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.CandidateDTO;
import model.dto.CandidateSkillDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewCandidateController", urlPatterns = "/ViewCandidate")
public class ViewCandidateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CandidateDAO candidateDAO = new CandidateDAO();
        String candidate_id_str = req.getParameter("candidateId");
        int candidateId = Integer.parseInt(candidate_id_str);
        try {
            CandidateDTO candidateDTO = candidateDAO.viewCandidateInfo(candidateId);
            req.setAttribute("candidate", candidateDTO);
            List<CandidateSkillDTO> candidateSkillDTO = candidateDAO.viewSKillOfCandidate(candidateId);
            req.setAttribute("candidateSkill", candidateSkillDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("./view/candidateDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
