package controller.Candidate;

import dao.CStatusDAO;
import dao.CandidateDAO;
import dao.SkillDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.CStatusDTO;
import model.dto.CandidateDTO;
import model.dto.CandidateSkillDTO;
import model.dto.SkillDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListCandidateController", urlPatterns = "/ListCandidate")
public class ListCandidateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String candidate_name = req.getParameter("candidate_name");
        String status_str = req.getParameter("status");
        CandidateDAO candidateDAO = new CandidateDAO();
        CStatusDAO cStatusDAO = new CStatusDAO();
        if((candidate_name == null || candidate_name.trim().equals("")) && (status_str == null || status_str.trim().equals("-1"))) {
            try {
                List<CandidateDTO> candidateList = candidateDAO.getListCandidate();
                req.setAttribute("candidateList", candidateList);
                List<CStatusDTO> cStatusList = cStatusDAO.getListcStatus();
                req.setAttribute("cStatusList", cStatusList);
                req.getRequestDispatcher("./view/candidateList.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if((candidate_name != null && !candidate_name.trim().equals("")) && (status_str == null || status_str.trim().equals("-1"))) {
            try {
                candidate_name = candidate_name.trim();
                List<CandidateDTO> candidateList = candidateDAO.getListCandidate(candidate_name);
                req.setAttribute("candidate_name", candidate_name);
                req.setAttribute("candidateList", candidateList);
                List<CStatusDTO> cStatusList = cStatusDAO.getListcStatus();
                req.setAttribute("cStatusList", cStatusList);
                req.getRequestDispatcher("./view/candidateList.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if((candidate_name == null || candidate_name.trim().equals("")) && (status_str != null && !status_str.trim().equals("-1"))) {
            try {
                int status = Integer.parseInt(status_str);
                List<CandidateDTO> candidateList = candidateDAO.getListCandidate(status);
                req.setAttribute("status", status_str);
                req.setAttribute("candidateList", candidateList);
                List<CStatusDTO> cStatusList = cStatusDAO.getListcStatus();
                req.setAttribute("cStatusList", cStatusList);
                req.getRequestDispatcher("./view/candidateList.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                candidate_name = candidate_name.trim();
                int status = Integer.parseInt(status_str);
                List<CandidateDTO> candidateList = candidateDAO.getListCandidate(candidate_name, status);
                req.setAttribute("candidate_name", candidate_name);
                req.setAttribute("status", status_str);
                req.setAttribute("candidateList", candidateList);
                List<CStatusDTO> cStatusList = cStatusDAO.getListcStatus();
                req.setAttribute("cStatusList", cStatusList);
                req.getRequestDispatcher("./view/candidateList.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
