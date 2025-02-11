package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation implements Serializable {
    private String reservationId;
    private String customerNic;
    private String cashierUsername;
    private Date pickUpDate;
    private String pickUpTime;
    private Date returnDate;
    private String returnTime;
    private String isDriverWant;
    private String driverNic;

    private ArrayList<ReservationDetail> reservationDetails;

    public Reservation(String reservationId, String customerNic, String  cashierUsername, Date pickUpDate, String pickUpTime, Date returnDate, String returnTime, String isDriverWant, ArrayList<ReservationDetail> reservationDetails) {
        this.reservationId = reservationId;
        this.customerNic = customerNic;
        this.cashierUsername = cashierUsername;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.isDriverWant = isDriverWant;
        this.reservationDetails = reservationDetails;
    }

    public Reservation(String reservationId, String customerNic, String cashierUsername, Date pickUpDate, String pickUpTime, Date returnDate, String returnTime, String isDriverWant) {
        this.reservationId = reservationId;
        this.customerNic = customerNic;
        this.cashierUsername = cashierUsername;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.isDriverWant = isDriverWant;
    }
}
