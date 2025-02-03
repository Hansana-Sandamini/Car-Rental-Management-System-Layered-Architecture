package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.CustomerDTO;
import lk.ijse.rdfcarrentals.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    void saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerNICs() throws SQLException, ClassNotFoundException;
    Customer searchCustomer(String selectedCustomerNIC) throws SQLException, ClassNotFoundException;
    void deleteCustomer(String selectedCustomer) throws SQLException, ClassNotFoundException;
}
