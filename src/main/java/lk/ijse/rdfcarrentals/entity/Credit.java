package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Credit implements Serializable {
    private String creditId;
    private String reservationId;
    private Double totalAmount;
    private Double amountPaid;
    private Double amountToPay;
    private Date dueDate;
}
