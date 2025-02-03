package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerTM implements Comparable<CustomerTM> {
    private String nic;
    private String name;
    private String address;
    private String email;
    private String contactNumber;

    @Override
    public int compareTo(CustomerTM o) {
        return nic.compareTo(o.getNic());
    }
}

//@Override
//public int compareTo(CashierTM o) {
//    return userName.compareTo(o.getUserName());
//}