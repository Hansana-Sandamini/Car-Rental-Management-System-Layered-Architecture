package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDetail implements Serializable {
    private String reservationId;
    private String licensePlateNo;
    private Double driverCost;
    private Double totalAmount;
}
