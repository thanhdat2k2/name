package model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CStatusDTO {
    private int cStatusId;
    private String cStatusName;
}
