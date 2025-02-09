package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.CustomerDAO;
import lk.ijse.rdfcarrentals.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer();
            customer.setNic(rst.getString("nic"));
            customer.setName(rst.getString("name"));
            customer.setAddress(rst.getString("address"));
            customer.setEmail(rst.getString("email"));
            customer.setContactNumber(rst.getString("contact_number"));
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public void save(Customer customer) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO customer VALUES (?,?,?,?,?)",
                customer.getNic(),
                customer.getName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getContactNumber()
        );
    }

    @Override
    public void update(Customer customer) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "UPDATE customer SET name = ?, address = ?, email = ?, contact_number = ? WHERE nic = ?",
                customer.getName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getContactNumber(),
                customer.getNic()
        );
    }

    @Override
    public void delete(String selectedCustomer) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM customer WHERE nic = ?", selectedCustomer);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Customer search(String selectedCustomerNIC) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE nic = ?", selectedCustomerNIC);
        if (rst.next()) {
            Customer customer = new Customer(
                    rst.getString("nic"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("email"),
                    rst.getString("contact_number")
            );
            return customer;
        }
        return null;
    }

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT nic FROM customer");

        ArrayList<String> customerNICs = new ArrayList<>();

        while (rst.next()) {
            customerNICs.add(rst.getString(1));
        }
        return customerNICs;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
