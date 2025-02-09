package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment implements Serializable {
    private String paymentId;
    private String reservationId;
    private String billId;
    private String paymentMethod;
    private Double amount;
    private Date date;
    private String time;

    public Payment(String paymentId, String reservationId, String paymentMethod, double amount, Date date, String time) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }
}
