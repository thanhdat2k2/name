package controller.Candidate;

import dao.CandidateDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.CandidateDTO;

import java.io.IOException;

@WebServlet(name = "DeleteCandidateController", urlPatterns = "/DeleteCandidate")
public class DeleteCandidateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CandidateDAO candidateDAO = new CandidateDAO();
        String candidate_id_str = req.getParameter("candidate_id");
        int candidate_id = Integer.parseInt(candidate_id_str);
        try {
            int candidate_delete_id = candidateDAO.deleteCandidate(candidate_id);
            req.setAttribute("candidate_delete_id", candidate_delete_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("./view/candidateList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
