package lk.ijse.rdfcarrentals.dao.custom;

import lk.ijse.rdfcarrentals.dao.CrudDAO;
import lk.ijse.rdfcarrentals.entity.DriverAssignment;

import java.sql.SQLException;

public interface DriverAssignmentDAO extends CrudDAO<DriverAssignment> {
    String getDriverNicByLicensePlate(String licensePlateNo) throws SQLException, ClassNotFoundException;
}
