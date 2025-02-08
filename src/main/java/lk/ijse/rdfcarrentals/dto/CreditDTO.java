package lk.ijse.rdfcarrentals.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditDTO {
    private String creditId;
    private String reservationId;
    private Double totalAmount;
    private Double amountPaid;
    private Double amountToPay;
    private Date dueDate;
}
