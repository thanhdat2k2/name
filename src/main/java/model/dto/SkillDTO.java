package model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SkillDTO {
    private int skillId;
    private String skillName;
}
