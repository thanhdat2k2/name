package controller.User;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "viewUserServlet", value = "/viewUser-servlet")
public class viewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        try {
            List<User> allUser = userDAO.getAllUser();
            request.setAttribute("allUser", allUser);
            request.getRequestDispatcher("./view/userList.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
