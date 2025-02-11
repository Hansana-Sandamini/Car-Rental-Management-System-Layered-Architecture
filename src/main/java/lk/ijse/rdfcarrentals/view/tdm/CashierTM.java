package lk.ijse.rdfcarrentals.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CashierTM implements Comparable<CashierTM> {
    private String userName;
    private String password;
    private String name;
    private String contactNumber;
    private String email;

    @Override
    public int compareTo(CashierTM o) {
        return userName.compareTo(o.getUserName());
    }
}
