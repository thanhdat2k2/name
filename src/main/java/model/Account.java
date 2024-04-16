package model;


public class Account {
    private int accountId;
    private String username;
    private String password;
    private int roleId;
    private int status;

    public Account() {
    }

    public Account(String username, String password, int roleId, int status) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
    }

    public Account(int accountId, String username, String password, int roleId, int status) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
