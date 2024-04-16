package model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PositionDTO {
    private int positionId;
    private String positionName;
}
