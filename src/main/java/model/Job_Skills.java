package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Job_Skills {
    private int jsId;
    private int jobId;
    private int benefitId;
}
