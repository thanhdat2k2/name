package controller;

import dao.AccountDAO;
import dao.UserDAO;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ForgotPassword;

import java.io.IOException;


@WebServlet(name = "forgotPasswordServlet", value = "/ForgotPassword-servlet")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./view/forgot-password.jsp").forward(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email").trim();

        UserDAO userDAO = new UserDAO();


        if (userDAO.isEmailExist(email)) {
            ForgotPassword forgot = new ForgotPassword();
            String new_pass = forgot.getNewPassword();
            boolean sent = forgot.sendMail(email, new_pass);

            if(sent){
                AccountDAO adao = new AccountDAO();

                if (userDAO.getUserByEmail(email) != null) {
                    adao.changePassword(new_pass, userDAO.getUserByEmail(email).getAccountId());
                } else {
                    adao.changePassword(new_pass, userDAO.getUserByEmail(email).getAccountId());
                }
                request.setAttribute("success", "Reset password successfully! Please check your mail.");
                request.getRequestDispatcher("./view/login.jsp").forward(request, response);
            } else {
                request.setAttribute("mess", "Can not send mail.");
                request.setAttribute("last_email", email);
                request.getRequestDispatcher("./view/forgot-password.jsp").forward(request, response);
            }
        }
        else {
            request.setAttribute("mess", "Email is not linked to an account");
            request.setAttribute("last_email", email);
            request.getRequestDispatcher("./view/forgot-password.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
