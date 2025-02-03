package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.CustomerBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.rdfcarrentals.dto.CustomerDTO;
import lk.ijse.rdfcarrentals.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO, SuperBO {

    CustomerDAOImpl customerDAO = (CustomerDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(
                    customer.getNic(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getContactNumber()
            ));
        }
        return customerDTOS;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        customerDAO.save(new Customer(
                customerDTO.getNic(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getContactNumber()
        ));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(
                customerDTO.getNic(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getContactNumber()
        ));
    }

    @Override
    public ArrayList<String> getAllCustomerNICs() throws SQLException, ClassNotFoundException {
        return customerDAO.loadAllIDs();
    }

    @Override
    public Customer searchCustomer(String selectedCustomerNIC) throws SQLException, ClassNotFoundException {
        return customerDAO.search(selectedCustomerNIC);
    }

    @Override
    public void deleteCustomer(String selectedCustomer) throws SQLException, ClassNotFoundException {
        customerDAO.delete(selectedCustomer);
    }
}
