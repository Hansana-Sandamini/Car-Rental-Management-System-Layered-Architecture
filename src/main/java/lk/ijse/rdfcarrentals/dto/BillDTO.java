package lk.ijse.rdfcarrentals.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillDTO {
    private String billId;
    private String paymentId;
    private String creditId;
    private String description;
    private LocalDate issueDate;

//    public BillDTO(String billId, String paymentId, String description, Date issueDate) {
//        this.billId = billId;
//        this.paymentId = paymentId;
//        this.description = description;
//        this.issueDate = issueDate;
//    }

//    public BillDTO(String text, String text1, String description, LocalDate issueDate) {
//        this.billId = text;
//        this.paymentId = text1;
//        this.description = description;
//        this.issueDate = issueDate.atStartOfDay().atZone(ZoneId.systemDefault())
//    }
}
