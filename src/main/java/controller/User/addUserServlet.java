package controller.User;

import dao.AccountDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "addUserServlet", value = "/addUser-servlet")
public class addUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./view/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("date");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        String note = request.getParameter("note");

        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("rePassword", rePassword);
        request.setAttribute("fullName", fullName);
        request.setAttribute("email", email);
        request.setAttribute("dob",dobStr);
        request.setAttribute("address", address);
        request.setAttribute("phoneNumber", phoneNumber);
//        request.setAttribute("genderId", genderId);
//        request.setAttribute("roleId", roleId);
//        request.setAttribute("departmentId", departmentId);
//        request.setAttribute("status", status);
        request.setAttribute("note", note);

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || dobStr.isEmpty() ||
                address.isEmpty() || phoneNumber.isEmpty() || note.isEmpty()) {
            request.getRequestDispatcher("./view/addUser.jsp?error=missing_info1").forward(request, response);
            return;
        }

        String genderIdParameter = request.getParameter("genderId");
        int genderId;
        if (genderIdParameter == null || genderIdParameter.isEmpty() || genderIdParameter.equals("Select a gender")) {
            request.getRequestDispatcher("./view/addUser.jsp?error=missing_info2").forward(request, response);
            return;
        } else {
            genderId = Integer.parseInt(genderIdParameter);
        }

        String roleIdParameter = request.getParameter("roleId");
        int roleId;
        if (roleIdParameter == null || roleIdParameter.isEmpty() || roleIdParameter.equals("Select a Roles")) {
            request.getRequestDispatcher("./view/addUser.jsp?error=missing_info3").forward(request, response);
            return;
        } else {
            roleId = Integer.parseInt(roleIdParameter);
        }

        String departmentIdParameter = request.getParameter("departmentId");
        int departmentId;
        if (departmentIdParameter == null || departmentIdParameter.isEmpty() || departmentIdParameter.equals("Select a Department")) {
            request.getRequestDispatcher("./view/addUser.jsp?error=missing_info4").forward(request, response);
            return;
        } else {
            departmentId = Integer.parseInt(departmentIdParameter);
        }

        String statusParameter = request.getParameter("status");
        int status;
        if (statusParameter == null || statusParameter.isEmpty() || statusParameter.equals("Select a Status")) {
            request.getRequestDispatcher("./view/addUser.jsp?error=missing_info5").forward(request, response);
            return;
        } else {
            status = Integer.parseInt(statusParameter);
        }

        request.setAttribute("genderId", genderId);
        request.setAttribute("roleId", roleId);
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("status", status);

        if(username != null && username.length() > 30){
            request.getRequestDispatcher("./view/addUser.jsp?error=usernameError").forward(request, response);
            return;
        }
        if (phoneNumber != null && phoneNumber.length() > 10) {
            request.getRequestDispatcher("./view/addUser.jsp?error=phoneError").forward(request, response);
            return;
        }

        if (email != null && email.length() > 50) {
            request.getRequestDispatcher("./view/addUser.jsp?error=emailError").forward(request, response);
            return;
        }




        AccountDAO accountDAO = new AccountDAO();
        UserDAO userDAO = new UserDAO();

        if (!phoneNumber.matches("0[0-9]{9}")) {
            request.getRequestDispatcher("./view/addUser.jsp?error=invalidFormatPhone").forward(request, response);
            return;
        }
        try {
            if(userDAO.isPhoneExist(phoneNumber)){
                request.getRequestDispatcher("./view/addUser.jsp?error=phoneExist").forward(request, response);
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (!email.matches("[a-zA-Z0-9]{6,}@gmail\\.com")) {
            request.getRequestDispatcher("./view/addUser.jsp?error=invalidFormatEmail").forward(request, response);
            return;
        }
        try {
            if(userDAO.isPhoneExist(phoneNumber)){
                request.getRequestDispatcher("./view/addUser.jsp?error=emailExist").forward(request, response);
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!password.equals(rePassword)) {
            request.getRequestDispatcher("./view/addUser.jsp?error=rePassWordError").forward(request, response);
            return;
        }



        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            Account account = new Account(username, password, roleId, status);
            boolean accountCreated = accountDAO.creatAccount(account);

            User user = new User(fullName, dob, phoneNumber, roleId, email, address, genderId, accountDAO.getNewestAccount().getAccountId(), departmentId, note);
            userDAO.createUser(user);

            String message = "Success";
            request.setAttribute("msg", message);
            request.setAttribute("successMessage", true);
            List<User> allUser = userDAO.getAllUser();
            request.setAttribute("allUser", allUser);
            request.getRequestDispatcher("./view/userList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
