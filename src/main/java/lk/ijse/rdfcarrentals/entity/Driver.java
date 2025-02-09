package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver implements Serializable {
    private String nic;
    private String name;
    private String email;
    private String availabilityStatus;
    private String contactNumber;
    private Double pricePerKm;

    private ArrayList<DriverAssignment> driverAssignments;

    public Driver(String nic, String name, String email, String availabilityStatus, String contactNumber, Double pricePerKm) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.availabilityStatus = availabilityStatus;
        this.contactNumber = contactNumber;
        this.pricePerKm = pricePerKm;
    }
}
