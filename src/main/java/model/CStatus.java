package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CStatus {
    private int cStatusId;
    private String cStatusName;
}
