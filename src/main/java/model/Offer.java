package model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Offer {
    private int offerId;
    private int candidateId;
    private int positionId;
    private int approverId;
    private int interviewId;
    private Date contractFrom;
    private Date contractTo;
    private int interviewNote;
    private int offerStatus;
    private int contractId;
    private int levelId;
    private int departmentId;
    private int recruiterId;
    private Date dueDate;
    private String basicSalary;
    private String offerNote;
}
