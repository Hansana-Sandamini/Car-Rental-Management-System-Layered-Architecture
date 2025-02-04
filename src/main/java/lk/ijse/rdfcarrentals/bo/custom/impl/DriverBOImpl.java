package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.DriverBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.DriverAssignmentDAOImpl;
import lk.ijse.rdfcarrentals.dao.custom.impl.DriverDAOImpl;
import lk.ijse.rdfcarrentals.dto.DriverDTO;
import lk.ijse.rdfcarrentals.entity.Driver;
import lk.ijse.rdfcarrentals.entity.DriverAssignment;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBOImpl implements DriverBO, SuperBO {

    DriverDAOImpl driverDAO = (DriverDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
    DriverAssignmentDAOImpl driverAssignmentDAO = (DriverAssignmentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER_ASSIGNMENT);

    @Override
    public ArrayList<DriverDTO> getAllDrivers() throws SQLException, ClassNotFoundException {
        ArrayList<DriverDTO> driverDTOS = new ArrayList<>();
        ArrayList<Driver> drivers = driverDAO.getAll();
        for (Driver driver : drivers) {
            driverDTOS.add(new DriverDTO(
                    driver.getNic(),
                    driver.getName(),
                    driver.getEmail(),
                    driver.getAvailabilityStatus(),
                    driver.getContactNumber(),
                    driver.getPricePerKm()
            ));
        }
        return driverDTOS;
    }

    @Override
    public void saveDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        driverDAO.save(new Driver(
                driverDTO.getNic(),
                driverDTO.getName(),
                driverDTO.getEmail(),
                driverDTO.getAvailabilityStatus(),
                driverDTO.getContactNumber(),
                driverDTO.getPricePerKm()
        ));

        if (driverDTO.getDriverAssignments() != null) {
            for (DriverAssignment driverAssignment : driverDTO.getDriverAssignments()) {
                driverAssignmentDAO.save(driverAssignment);
            }
        }
    }

    @Override
    public void updateDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        driverDAO.update(new Driver(
                driverDTO.getNic(),
                driverDTO.getName(),
                driverDTO.getEmail(),
                driverDTO.getAvailabilityStatus(),
                driverDTO.getContactNumber(),
                driverDTO.getPricePerKm()
        ));
    }

    @Override
    public void deleteDriver(String selectedDriver) throws SQLException, ClassNotFoundException {
        driverDAO.delete(selectedDriver);
    }
}
