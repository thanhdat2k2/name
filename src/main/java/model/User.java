package model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private int userId;
    private String fullName;
    private Date doB;
    private String phoneNumber;
    private int positionRoles;
    private String email;
    private String address;
    private int genderId;
    private int accountId;
    private int departmentId;
    private String note;

    public User(String fullName, Date doB, String phoneNumber, int positionRoles, String email, String address, int genderId, int accountId, int departmentId, String note) {
        this.fullName = fullName;
        this.doB = doB;
        this.phoneNumber = phoneNumber;
        this.positionRoles = positionRoles;
        this.email = email;
        this.address = address;
        this.genderId = genderId;
        this.accountId = accountId;
        this.departmentId = departmentId;
        this.note = note;
    }
}
