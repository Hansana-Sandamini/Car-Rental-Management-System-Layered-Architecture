package lk.ijse.rdfcarrentals.dao.custom;

import lk.ijse.rdfcarrentals.dao.CrudDAO;
import lk.ijse.rdfcarrentals.entity.Driver;

import java.sql.SQLException;

public interface DriverDAO extends CrudDAO<Driver> {
    boolean updateDriverAvailability(String nic, String status) throws SQLException, ClassNotFoundException;
}
