package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DriverTM implements Comparable<DriverTM> {
    private String nic;
    private String name;
    private String email;
    private String availabilityStatus;
    private String contactNumber;
    private Double pricePerKm;

    @Override
    public int compareTo(DriverTM o) {
        return nic.compareTo(o.getNic());
    }
}
