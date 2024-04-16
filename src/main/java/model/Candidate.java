package model;

import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Candidate {
    private int candidateId;
    private String candidateName;
    private Date doB;
    private String phone;
    private String email;
    private String address;
    private int genderId;
    private String cv_att;
    private int positionId;
    private int recruiterId;
    private String note;
    private int cStatusId;
    private int yoE;
    private int levelId;
    private String ownerHR;
}
