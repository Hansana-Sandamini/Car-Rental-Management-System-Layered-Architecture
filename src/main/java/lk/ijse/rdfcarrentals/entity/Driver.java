package lk.ijse.rdfcarrentals.entity;

import lk.ijse.rdfcarrentals.dto.DriverAssignmentDTO;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver {
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
