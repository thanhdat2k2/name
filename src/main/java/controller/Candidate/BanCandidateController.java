package controller.Candidate;

import dao.CandidateDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BanCandidateController", urlPatterns = "/BanCandidate")
public class BanCandidateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int candidate_id = Integer.parseInt(req.getParameter("candidate_id"));
        CandidateDAO candidateDAO = new CandidateDAO();
        try {
            int candidateBanId = candidateDAO.banCandidate(candidate_id);
            req.setAttribute("candidateBanId", candidateBanId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
