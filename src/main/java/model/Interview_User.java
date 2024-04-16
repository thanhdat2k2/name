package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Interview_User {
    private int iuId;
    private int interviewId;
    private int interviewerId;
}
