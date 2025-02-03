package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.DriverAssignmentDAO;
import lk.ijse.rdfcarrentals.dto.DriverAssignmentDTO;
import lk.ijse.rdfcarrentals.entity.DriverAssignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverAssignmentDAOImpl implements DriverAssignmentDAO {

    @Override
    public ArrayList<DriverAssignment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM driver_assignment");

        ArrayList<DriverAssignment> driverAssignments = new ArrayList<>();

        while (rst.next()) {
            DriverAssignment driverAssignment = new DriverAssignment();

            driverAssignment.setLicensePlateNo(rst.getString("license_plate_no"));
            driverAssignment.setDriverNic(rst.getString("driver_nic"));
            driverAssignment.setPricePerKm(rst.getDouble("price_per_km"));
            driverAssignment.setDate(rst.getDate("date"));
            driverAssignment.setTime(rst.getString("time"));

            driverAssignments.add(driverAssignment);
        }
        return driverAssignments;
    }

    @Override
    public void save(DriverAssignment driverAssignment) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO driver_assignment VALUES (?,?,?,?,?)",
                driverAssignment.getLicensePlateNo(),
                driverAssignment.getDriverNic(),
                driverAssignment.getPricePerKm(),
                driverAssignment.getDate(),
                driverAssignment.getTime()
        );
    }

    @Override
    public void update(DriverAssignment driverAssignment) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

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
    public DriverAssignment search(String id) throws SQLException, ClassNotFoundException {
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
