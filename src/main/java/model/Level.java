package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Level {
    private int levelId;
    private String levelName;
}
