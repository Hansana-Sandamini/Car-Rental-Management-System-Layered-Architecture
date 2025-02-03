package lk.ijse.rdfcarrentals.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepairDTO {
    private String repairId;
    private String description;
    private Date date;
    private Double cost;
    private String licensePlateNo;
}
