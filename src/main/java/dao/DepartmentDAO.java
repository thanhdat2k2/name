package dao;

import connection.SQLServerConnection;
import model.Department;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends SQLServerConnection {
    public List<Department> getAllDepartment() throws Exception{
        List<Department> ls = new ArrayList<>();
        String sql = "Select * from [Department]";
        try (
                //Mở kết nối
                Connection connection = SQLServerConnection.getConnection();
                //Đưa câu query sang sql
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            // executeQuery nhan ve bang
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt("department_id"));
                department.setDepartmentName(rs.getString("department_name"));
                ls.add(department);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return ls;
    }

    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        try {
            System.out.println(departmentDAO.getAllDepartment());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
