package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car implements Serializable {
    private String licensePlateNo;
    private String model;
    private String colour;
    private double dailyRate;
    private double monthlyRate;
    private String availabilityStatus;
    private String typeId;
}
