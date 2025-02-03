package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer implements Serializable {
    private String nic;
    private String name;
    private String address;
    private String email;
    private String contactNumber;
}
