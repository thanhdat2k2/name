package model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LevelDTO {
    private int levelId;
    private String levelName;
}
