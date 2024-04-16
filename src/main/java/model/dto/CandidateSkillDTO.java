package model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CandidateSkillDTO {
    private int candidate_id;
    private int skill_id;
    private String candidate_name;
    private String skill_name;
}
