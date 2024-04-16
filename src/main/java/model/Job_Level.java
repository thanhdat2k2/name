package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Job_Level {
    private int jlId;
    private int jobId;
    private int levelId;
}
