package model;

import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Interview {
    private int interviewId;
    private String scheduleTitle;
    private int candidateId;
    private Date scheduleTime;
    private Date timeFrom;
    private Date timeTo;
    private String note;
    private String location;
    private int recruiterId;
    private String meetingLink;
}
