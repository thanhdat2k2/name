package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Candidate_Skill {
    private int csId;
    private int candidateId;
    private int skillId;
}
