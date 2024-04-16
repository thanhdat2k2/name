package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Interview_Job {
    private int ijId;
    private int interviewId;
    private int jobId;
}
