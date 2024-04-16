package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Department {
    private int departmentId;
    private String departmentName;
}
