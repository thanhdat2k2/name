package dao;

import connection.SQLServerConnection;
import model.Account;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends SQLServerConnection {
    public Account getAccount(String user, String pass) throws Exception {
        String sql = "select * from Account where username = ? and [password] = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public int getAccountStatusById(int accountId) throws Exception {
        int status = 0;

        String sql = "SELECT status FROM Account WHERE account_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, accountId);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getInt("status");
                }
            }
        }
        return status;
    }

    public int getRoleIdByAccount(int accountId) throws Exception {
        int role_id = 0;

        String sql = "SELECT role_id FROM Account WHERE account_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, accountId);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    role_id = resultSet.getInt("role_id");
                }
            }
        }
        return role_id;
    }

    public boolean creatAccount(Account account) throws Exception{
        int rowAffected = 0;
        String sql = "INSERT INTO Account (username, password, role_id, status) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, account.getUsername());
            ps.setString(2,account.getPassword());
            ps.setInt(3,account.getRoleId());
            ps.setInt(4,account.getStatus());
            rowAffected = ps.executeUpdate();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return rowAffected > 0;
    }

    public Account getNewestAccount() throws Exception {
        String sql = "SELECT TOP 1 * FROM Account ORDER BY account_id DESC";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs;
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new Account(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5)
                    );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
    }

    public Account checkUsernameExist(String user)  throws Exception{
        String sql = "select * from Account where username = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean updateAccountByStatus(Account account) throws Exception {
        boolean updated = false;
        String sql = "UPDATE Account SET status = ? WHERE account_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1,account.getStatus());
            ps.setInt(2,account.getAccountId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0){
                updated = true;
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return updated;
    }

    public void changePassword(String pass, int id) throws Exception {
        String sql = "update Account set password=? where account_id = ?";
        try (
                Connection connection = SQLServerConnection.getConnection();
                PreparedStatement pre = connection.prepareStatement(sql)
        ) {
            pre.setString(1, pass);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        try {
            System.out.println(accountDAO.checkUsernameExist("admin1"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
