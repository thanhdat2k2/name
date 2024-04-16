package dao;

import connection.SQLServerConnection;
import model.Gender;
import model.Position;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends SQLServerConnection {
    public List<Role> getAllRole() throws Exception{
        List<Role> ls = new ArrayList<>();
        String sql = "Select * from [Roles]";
        try (
                //Mở kết nối
                Connection connection = SQLServerConnection.getConnection();
                //Đưa câu query sang sql
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            // executeQuery nhan ve bang
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                ls.add(role);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return ls;
    }

    public Role getRoleById(int roleId) throws SQLException {
        Role role = null;
        String query = "SELECT role_name FROM roles WHERE role_id = ?";

        try ( Connection connection = SQLServerConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String roleName = resultSet.getString("role_name");
                    role = new Role(roleId, roleName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    public static void main(String[] args) {
        RoleDAO roleDAO = new RoleDAO();
        try {
            System.out.println(roleDAO.getRoleById(2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
