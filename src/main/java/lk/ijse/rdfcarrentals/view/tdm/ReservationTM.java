package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationTM implements Comparable<ReservationTM> {
    private String reservationId;
    private String customerNic;
    private String cashierUsername;
    private Date pickUpDate;
    private String pickUpTime;
    private Date returnDate;
    private String returnTime;
    private String isDriverWant;

    @Override
    public int compareTo(ReservationTM o) {
        return reservationId.compareTo(o.getReservationId());
    }
}
