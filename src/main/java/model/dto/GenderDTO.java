package model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GenderDTO {
    private int genderId;
    private String genderName;
}
