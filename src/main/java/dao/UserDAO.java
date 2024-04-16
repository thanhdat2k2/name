package dao;

import connection.SQLServerConnection;
import model.Account;
import model.User;
import model.dto.SkillDTO;
import model.dto.UserDTO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends SQLServerConnection {
    public List<User> getAllUser() throws Exception {
        List<User> ls = new ArrayList<>();
        String sql = "Select * from [User]";
        try (
                //Mở kết nối
                Connection connection = SQLServerConnection.getConnection();
                //Đưa câu query sang sql
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            // executeQuery nhan ve bang
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("fullname"));
                user.setDoB(rs.getDate("dob"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPositionRoles(rs.getInt("position_roles"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setGenderId(rs.getInt("gender_id"));
                user.setAccountId(rs.getInt("account_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setNote(rs.getString("note"));
                ls.add(user);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return ls;
    }

    public List<User> searchUser(String text) throws Exception {
        List<User> ls = new ArrayList<>();
        String sql = "SELECT u.*, p.position_name, a.status "
                + "FROM [user] u "
                + "JOIN [position] p ON u.position_roles = p.position_id "
                + "JOIN account a ON u.account_id = a.account_id "
                + " WHERE u.fullname LIKE ? OR u.email LIKE ? OR u.phone_number LIKE ? OR p.position_name LIKE ? OR a.status LIKE ?";
        try (
                //Mở kết nối
                Connection connection = SQLServerConnection.getConnection();
                //Đưa câu query sang sql
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            ps.setString(3, "%" + text + "%");
            ps.setString(4, "%" + text + "%");
            ps.setString(5, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("fullname"));
                user.setDoB(rs.getDate("dob"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPositionRoles(rs.getInt("position_roles"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setGenderId(rs.getInt("gender_id"));
                user.setAccountId(rs.getInt("account_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setNote(rs.getString("note"));
                ls.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ls;
    }

    public boolean createUser(User user) throws Exception {
        int rowAffected = 0;
        String sql = "INSERT INTO [User] (fullname, dob, phone_number, position_roles, email, address, gender_id, account_id, department_id, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, user.getFullName());
            statement.setDate(2, new java.sql.Date(user.getDoB().getTime()));
            statement.setString(3, user.getPhoneNumber());
            statement.setInt(4, user.getPositionRoles());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setInt(7, user.getGenderId());
            statement.setInt(8, user.getAccountId());
            statement.setInt(9, user.getDepartmentId());
            statement.setString(10, user.getNote());
            rowAffected = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rowAffected > 0;
    }

    public boolean isEmailExist(String email) throws Exception {
        String sql = "SELECT COUNT(*) FROM [User] WHERE email = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isPhoneExist(String phone) throws Exception {
        String sql = "SELECT COUNT(*) FROM [User] WHERE phone_number = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User getUserById(int userId) throws Exception {
        String sql = "Select * from [user] where user_Id = ?";
        try (Connection connection = SQLServerConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("fullname"));
                user.setDoB(rs.getDate("dob"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPositionRoles(rs.getInt("position_roles"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setGenderId(rs.getInt("gender_id"));
                user.setAccountId(rs.getInt("account_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setNote(rs.getString("note"));
                return user;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return null;
    }

    public boolean updateUser(User user) throws Exception {
        boolean updated = false;
        String sql = "Update [User] SET fullname = ?, dob = ?, phone_number = ?, position_roles = ?, email = ? , address = ?, " +
                " gender_id = ?, account_id = ?, department_id = ?, note = ? where user_id = ? ";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, user.getFullName());
            ps.setDate(2, new java.sql.Date(user.getDoB().getTime()));
            ps.setString(3, user.getPhoneNumber());
            ps.setInt(4,user.getPositionRoles());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAddress());
            ps.setInt(7,user.getGenderId());
            ps.setInt(8,user.getAccountId());
            ps.setInt(9,user.getDepartmentId());
            ps.setString(10, user.getNote());
            ps.setInt(11,user.getUserId());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0){
                updated = true;
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return updated;
    }

    public List<User> searchByRole(int roleId) {
        UserDAO userDAO = new UserDAO();
        List<User> userList = null;
        try {
            userList = userDAO.getAllUser();
            List<User> filteredUsers = new ArrayList<>();
            for (User user : userList) {
                if (roleId == user.getPositionRoles()) {
                    filteredUsers.add(user);
                }
            }
            return filteredUsers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByAccountID(int accountID) throws Exception{
        String sql = "select * from [User] where account_id = ?";
        try (Connection connection = SQLServerConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, accountID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("fullname"));
                user.setDoB(rs.getDate("dob"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPositionRoles(rs.getInt("position_roles"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setGenderId(rs.getInt("gender_id"));
                user.setAccountId(rs.getInt("account_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setNote(rs.getString("note"));
                return user;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public User getUserByEmail(String email) throws Exception {
        String sql = "Select * from [user] where email = ?";
        try (Connection connection = SQLServerConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("fullname"));
                user.setDoB(rs.getDate("dob"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPositionRoles(rs.getInt("position_roles"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setGenderId(rs.getInt("gender_id"));
                user.setAccountId(rs.getInt("account_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setNote(rs.getString("note"));
                return user;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return null;
    }

    public List<UserDTO> getUserIdAndName() throws Exception {
        List<UserDTO> userList = new ArrayList<>();
        String sql = "SELECT u.[user_id], u.fullname FROM [User] u";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(rs.getInt("user_id"));
                userDTO.setFullName(rs.getString("fullname"));
                userList.add(userDTO);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return userList;
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        List<User> updateUser = null;
        try {
            System.out.println(userDAO.getUserByEmail("anhkhuong989@gmail.com"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
