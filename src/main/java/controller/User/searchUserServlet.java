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

@WebServlet(name = "searchUserServlet", value = "/searchUser-servlet")
public class searchUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("search");

        UserDAO userDAO = new UserDAO();
        List<User> allUser = null;
        try {
            allUser = userDAO.searchUser(keyword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String selectedRole = request.getParameter("role");
        if (selectedRole != null && !selectedRole.isEmpty()) {
            int roleId = Integer.parseInt(selectedRole);
             allUser = userDAO.searchByRole(roleId);
            request.setAttribute("allUser", allUser);
        }
        request.setAttribute("allUser", allUser);
        request.getRequestDispatcher("./view/userList.jsp").forward(request, response);
    }
}
