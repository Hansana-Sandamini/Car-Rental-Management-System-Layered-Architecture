package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverAssignment implements Serializable {
    private String licensePlateNo;
    private String driverNic;
    private Double pricePerKm;
    private Date date;
    private String time;
}
