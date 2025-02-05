package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDetailTM implements Comparable<ReservationDetailTM> {
    private String reservationId;
    private String licensePlateNo;
    private Double driverCost;
    private Double totalAmount;

    @Override
    public int compareTo(ReservationDetailTM o) {
        return reservationId.compareTo(o.getReservationId());
    }
}
