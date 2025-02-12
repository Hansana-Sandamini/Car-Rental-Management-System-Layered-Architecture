package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin implements Serializable {
    private String userName;
    private String password;
    private String name;
    private String email;
    private String contactNumber;
}
