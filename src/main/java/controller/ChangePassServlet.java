package controller;

import dao.AccountDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import utils.Validation;

import java.io.IOException;

@WebServlet(name = "ChangePassServlet", value = "/ChangePass-servlet")
public class ChangePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./view/change-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
//        String pass = request.getParameter("password");
        String newpass = request.getParameter("password");
        String re_newpass = request.getParameter("rePassword");

        UserDAO userDAO = new UserDAO();
        Validation valid = new Validation();
        AccountDAO accountDAO = new AccountDAO();

        if (valid.checkPassword(newpass) == false) {
            request.setAttribute("mess", "Password must be at least 6 chars, contains at least 1 digit, 1 special char, 1 lowercase letter, 1 uppercase letter and no whitespace");
            request.getRequestDispatcher("./view/change-password.jsp").forward(request, response);
        } else if (!newpass.equals(re_newpass)) {
            request.setAttribute("mess", "Password confirmation doesn't match Password");
            request.getRequestDispatcher("./view/change-password.jsp").forward(request, response);
        } else {
            try {
                accountDAO.changePassword(newpass, acc.getAccountId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            session.removeAttribute("admin");
            request.setAttribute("success", "Password changed successfully! Please log in again.");
            request.getRequestDispatcher("./view/login.jsp").forward(request, response);
        }
    }
}