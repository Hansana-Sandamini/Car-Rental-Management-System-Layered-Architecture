package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Repair implements Serializable {
    private String repairId;
    private String description;
    private Date date;
    private Double cost;
    private String licensePlateNo;
}
