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
}
