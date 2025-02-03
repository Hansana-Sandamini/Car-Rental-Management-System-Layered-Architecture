package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarTM implements Comparable<CarTM> {
    private String licensePlateNo;
    private String typeId;
    private String model;
    private String colour;
    private double dailyRate;
    private double monthlyRate;
    private String availabilityStatus;

    @Override
    public int compareTo(CarTM o) {
        return licensePlateNo.compareTo(o.getLicensePlateNo());
    }
}
