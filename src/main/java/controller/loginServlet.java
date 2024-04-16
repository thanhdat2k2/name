package controller;

import java.io.*;

import dao.AccountDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import model.User;


@WebServlet(name = "loginServlet", value = "/login-servlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");

        AccountDAO adao = new AccountDAO();
        UserDAO userDAO = new UserDAO();

        request.setAttribute("last_user", username);
        request.setAttribute("last_pass", password);

        Account a = null;
        try {
            a = adao.getAccount(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (a == null) {
            request.setAttribute("error", "Wrong username or password");
            request.getRequestDispatcher("./view/login.jsp").forward(request, response);
        } else if (a.getStatus() == 2) {
            request.setAttribute("error", "Your account is currently blocked");
            request.getRequestDispatcher("./view/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            switch (a.getRoleId()) {
                case 1:
                    try {
                        User admin = userDAO.getUserByAccountID(a.getAccountId());
                        session.setAttribute("admin", admin);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    response.sendRedirect("viewUser-servlet");
                    break;
                case 2:
                    response.sendRedirect("viewUser-servlet");
                    break;
                case 3:
                    response.sendRedirect("viewUser-servlet");
                    break;
                case 4:
                    response.sendRedirect("viewUser-servlet");
                    break;
            }
        }
    }
}
