package model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Job {
    private int jobId;
    private String jobTitle;
    private Date startDate;
    private Date endDate;
    private String salaryFrom;
    private String salaryTo;
    private String workingAddress;
    private int jobStatus;
    private String description;
}
