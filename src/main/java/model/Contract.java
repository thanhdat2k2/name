package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Contract {
    private int contractId;
    private String contractType;
}
