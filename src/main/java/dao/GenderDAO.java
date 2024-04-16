package dao;

import connection.SQLServerConnection;
import model.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenderDAO extends SQLServerConnection {
    public List<Gender> getAllGender() throws Exception{
        List<Gender> ls = new ArrayList<>();
        String sql = "Select * from [Gender]";
        try (
                //Mở kết nối
                Connection connection = SQLServerConnection.getConnection();
                //Đưa câu query sang sql
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            // executeQuery nhan ve bang
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gender gender = new Gender();
                gender.setGenderId(rs.getInt("gender_id"));
                gender.setGenderName(rs.getString("gender_name"));
                ls.add(gender);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return ls;
    }

    public static void main(String[] args) {
        GenderDAO genderDAO = new GenderDAO();
        try {
            System.out.println(genderDAO.getAllGender());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
