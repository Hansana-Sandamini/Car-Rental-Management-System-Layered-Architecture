package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverAssignmentTM implements Comparable<DriverAssignmentTM> {
    private String licensePlateNo;
    private String driverNic;
    private Double pricePerKm;
    private Date date;
    private String time;

    @Override
    public int compareTo(DriverAssignmentTM o) {
        return licensePlateNo.compareTo(o.getLicensePlateNo());
    }
}
