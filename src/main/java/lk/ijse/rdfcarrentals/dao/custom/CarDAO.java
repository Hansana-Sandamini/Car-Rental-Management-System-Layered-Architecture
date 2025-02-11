package lk.ijse.rdfcarrentals.dao.custom;

import lk.ijse.rdfcarrentals.dao.CrudDAO;
import lk.ijse.rdfcarrentals.entity.Car;

import java.sql.SQLException;

public interface CarDAO extends CrudDAO<Car> {
    boolean updateCarAvailability(String licensePlateNo, String status) throws SQLException, ClassNotFoundException;
}
