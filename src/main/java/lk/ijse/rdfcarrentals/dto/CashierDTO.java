package lk.ijse.rdfcarrentals.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CashierDTO {
    private String userName;
    private String password;
    private String name;
    private String contactNumber;
    private String email;
}