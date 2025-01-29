package lk.ijse.rdfcarrentals.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cashier implements Serializable {
    private String userName;
    private String password;
    private String name;
    private String contactNumber;
    private String email;

//    public Cashier() {}
//
//    public Cashier(String userName, String password, String name, String contactNumber, String email) {
//        this.userName = userName;
//        this.password = password;
//        this.name = name;
//        this.contactNumber = contactNumber;
//        this.email = email;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getContactNumber() {
//        return contactNumber;
//    }
//
//    public void setContactNumber(String contactNumber) {
//        this.contactNumber = contactNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Override
//    public String toString() {
//        return "Cashier{" +
//                "userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                ", name='" + name + '\'' +
//                ", contactNumber='" + contactNumber + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
