package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill implements Serializable {
    private String billId;
    private String paymentId;
    private String creditId;
    private String description;
    private LocalDate issueDate;
}
