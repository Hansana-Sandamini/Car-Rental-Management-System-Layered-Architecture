package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditTM implements Comparable<CreditTM> {
    private String creditId;
    private String reservationId;
    private Double totalAmount;
    private Double amountPaid;
    private Double amountToPay;
    private Date dueDate;

    @Override
    public int compareTo(CreditTM o) {
        return creditId.compareTo(o.getCreditId());
    }
}
