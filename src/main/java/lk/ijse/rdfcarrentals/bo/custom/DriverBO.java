package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.DriverDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverBO {
    ArrayList<DriverDTO> getAllDrivers() throws SQLException, ClassNotFoundException;
    void saveDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException;
    void updateDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException;
    void deleteDriver(String selectedDriver) throws SQLException, ClassNotFoundException;
}
