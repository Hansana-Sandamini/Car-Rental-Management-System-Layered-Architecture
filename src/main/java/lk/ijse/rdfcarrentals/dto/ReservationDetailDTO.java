package lk.ijse.rdfcarrentals.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDetailDTO {
    private String reservationId;
    private String licensePlateNo;
    private Double driverCost;
    private Double totalAmount;
}
