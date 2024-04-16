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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EditUserServlet", value = "/EditUser-servlet")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./view/editUser.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String rePassword = request.getParameter("rePassword");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("date");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        String note = request.getParameter("note");
        int userId = Integer.parseInt(request.getParameter("userId"));
        int accountId = Integer.parseInt(request.getParameter("accountId"));

//        request.setAttribute("username", username);
//        request.setAttribute("password", password);
//        request.setAttribute("rePassword", rePassword);
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

        if (fullName.isEmpty() || email.isEmpty() || dobStr.isEmpty() ||
                address.isEmpty() || phoneNumber.isEmpty() || note.isEmpty()) {
            request.getRequestDispatcher("./view/editUser.jsp?error=missing_info1").forward(request, response);
            return;
        }

        String genderIdParameter = request.getParameter("genderId");
        int genderId;
        if (genderIdParameter == null || genderIdParameter.isEmpty() || genderIdParameter.equals("Select a gender")) {
            request.getRequestDispatcher("./view/editUser.jsp?error=missing_info2").forward(request, response);
            return;
        } else {
            genderId = Integer.parseInt(genderIdParameter);
        }

        String roleIdParameter = request.getParameter("roleId");
        int roleId;
        if (roleIdParameter == null || roleIdParameter.isEmpty() || roleIdParameter.equals("Select a Roles")) {
            request.getRequestDispatcher("./view/editUser.jsp?error=missing_info3").forward(request, response);
            return;
        } else {
            roleId = Integer.parseInt(roleIdParameter);
        }

        String departmentIdParameter = request.getParameter("departmentId");
        int departmentId;
        if (departmentIdParameter == null || departmentIdParameter.isEmpty() || departmentIdParameter.equals("Select a Department")) {
            request.getRequestDispatcher("./view/editUser.jsp?error=missing_info4").forward(request, response);
            return;
        } else {
            departmentId = Integer.parseInt(departmentIdParameter);
        }

        String statusParameter = request.getParameter("status");
        int status;
        if (statusParameter == null || statusParameter.isEmpty() || statusParameter.equals("Select a Status")) {
            request.getRequestDispatcher("./view/editUser.jsp?error=missing_info5").forward(request, response);
            return;
        } else {
            status = Integer.parseInt(statusParameter);
        }

        request.setAttribute("genderId", genderId);
        request.setAttribute("roleId", roleId);
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("status", status);

//        if(username != null && username.length() > 30){
//            request.getRequestDispatcher("./view/addUser.jsp?error=usernameError").forward(request, response);
//            return;
//        }
        if (phoneNumber != null && phoneNumber.length() > 10) {
            request.getRequestDispatcher("./view/editUser.jsp?error=phoneError").forward(request, response);
            return;
        }

        if (email != null && email.length() > 50) {
            request.getRequestDispatcher("./view/editUser.jsp?error=emailError").forward(request, response);
            return;
        }




        AccountDAO accountDAO = new AccountDAO();
        UserDAO userDAO = new UserDAO();

        if (!phoneNumber.matches("0[0-9]{9}")) {
            request.getRequestDispatcher("./view/editUser.jsp?error=invalidFormatPhone").forward(request, response);
            return;
        }

        if (!email.matches("[a-zA-Z0-9]{6,}@gmail\\.com")) {
            request.getRequestDispatcher("./view/editUser.jsp?error=invalidFormatEmail").forward(request, response);
            return;
        }
//        if (!password.equals(rePassword)) {
//            request.getRequestDispatcher("./view/addUser.jsp?error=rePassWordError").forward(request, response);
//            return;
//        }



        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet EditPatientController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet EditPatientController at " + request.getContextPath() + "</h1>");
//            out.println(userId);
//            out.println(accountId);
//            out.println(fullName);
//            out.println(dob);
//            out.println(roleId);
//            out.println(email);
//            out.println(address);
//            out.println(genderId);
//            out.println("</body>");
//            out.println("</html>");
//        }

        Account accountUpdate = new Account();
        accountUpdate.setAccountId(accountId);
        accountUpdate.setStatus(status);
        try {
            boolean success = accountDAO.updateAccountByStatus(accountUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        User updateUser = new User();
        updateUser.setUserId(userId);
        updateUser.setFullName(fullName);
        updateUser.setDoB(dob);
        updateUser.setPhoneNumber(phoneNumber);
        updateUser.setPositionRoles(roleId);
        updateUser.setEmail(email);
        updateUser.setAddress(address);
        updateUser.setGenderId(genderId);
        updateUser.setAccountId(accountId);
        updateUser.setDepartmentId(departmentId);
        updateUser.setNote(note);
        try {
            boolean success = userDAO.updateUser(updateUser);
            if(success){
                response.sendRedirect("/ims_ee_project_war_exploded/viewUser-servlet");
            }else {
                response.sendRedirect("./view/editUser.jsp?userId=" + userId + "&error=update_failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}