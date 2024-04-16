package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Skill {
    private int skillId;
    private String skillName;
}
