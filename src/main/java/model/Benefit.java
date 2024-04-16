package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Benefit {
    private int benefitId;
    private String benefitName;
}
