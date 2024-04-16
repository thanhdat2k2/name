package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Job_Benefit {
    private int jbId;
    private int jobId;
    private int benefitId;
}
