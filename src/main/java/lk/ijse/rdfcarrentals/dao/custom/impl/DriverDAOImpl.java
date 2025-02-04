package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.DriverDAO;
import lk.ijse.rdfcarrentals.entity.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAOImpl implements DriverDAO {
    @Override
    public ArrayList<Driver> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM `driver`");

        ArrayList<Driver> drivers = new ArrayList<>();
        while (rst.next()) {
            Driver driver = new Driver();
            driver.setNic(rst.getString("nic"));
            driver.setName(rst.getString("name"));
            driver.setEmail(rst.getString("email"));
            driver.setAvailabilityStatus(rst.getString("availability_status"));
            driver.setContactNumber(rst.getString("contact_number"));
            driver.setPricePerKm(rst.getDouble("price_per_km"));
            driver.setDriverAssignments(new ArrayList<>());
            drivers.add(driver);
        }
        return drivers;
    }

    @Override
    public void save(Driver driver) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO driver VALUES (?,?,?,?,?,?)",
                driver.getNic(),
                driver.getName(),
                driver.getEmail(),
                driver.getAvailabilityStatus(),
                driver.getContactNumber(),
                driver.getPricePerKm()
        );
    }

    @Override
    public void update(Driver driver) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "UPDATE driver SET name = ?, email = ?, availability_status = ?, contact_number = ?, price_per_km = ? WHERE nic = ?",
                driver.getName(),
                driver.getEmail(),
                driver.getAvailabilityStatus(),
                driver.getContactNumber(),
                driver.getPricePerKm(),
                driver.getNic()
        );
    }

    @Override
    public void delete(String selectedDriver) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM driver WHERE nic = ?", selectedDriver);
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
    public Driver search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
