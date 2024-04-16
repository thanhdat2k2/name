package model.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CandidateDTO {

    private int candidateId;
    private String candidateName;
    private String email;
    private Date dob;
    private String address;
    private String phone;
    private int genderId;
    private String genderName;
    private String cvAtt;
    private int cStatusId;
    private String cStatusName;
    private int positionId;
    private String positionName;
    private int yoe;
    private int levelId;
    private String levelName;
    private int recruiterId;
    private String recruiterName;
    private String note;
    private String ownerHR;

    public CandidateDTO(int candidateId, String candidateName, String email, String phone, int positionId,
                        String positionName, String ownerHR, int cStatusId, String cStatusName) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.email = email;
        this.phone = phone;
        this.positionId = positionId;
        this.positionName = positionName;
        this.ownerHR = ownerHR;
        this.cStatusId = cStatusId;
        this.cStatusName = cStatusName;
    }

    public CandidateDTO(int candidateId, String candidateName, String email, Date dob, String address, String phone,
                        int genderId, String genderName, String cvAtt, int cStatusId, String cStatusName, int positionId,
                        String positionName, int yoe, int levelId, String levelName, int recruiterId,
                        String recruiterName, String note) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.genderId = genderId;
        this.genderName = genderName;
        this.cvAtt = cvAtt;
        this.cStatusId = cStatusId;
        this.cStatusName = cStatusName;
        this.positionId = positionId;
        this.positionName = positionName;
        this.yoe = yoe;
        this.levelId = levelId;
        this.levelName = levelName;
        this.recruiterId = recruiterId;
        this.recruiterName = recruiterName;
        this.note = note;
    }

    public CandidateDTO(int candidateId, String candidateName, String email, Date dob, String address, String phone, int genderId, String cvAtt, int positionId, int yoe, int levelId, int recruiterId, String note) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.genderId = genderId;
        this.cvAtt = cvAtt;
        this.positionId = positionId;
        this.yoe = yoe;
        this.levelId = levelId;
        this.recruiterId = recruiterId;
        this.note = note;
    }
}
