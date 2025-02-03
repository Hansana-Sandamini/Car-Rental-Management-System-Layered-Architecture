package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepairTM implements Comparable<RepairTM> {
    private String repairId;
    private String licensePlateNo;
    private String description;
    private Date date;
    private Double cost;

    @Override
    public int compareTo(RepairTM o) {
        return repairId.compareTo(o.getRepairId());
    }
}
