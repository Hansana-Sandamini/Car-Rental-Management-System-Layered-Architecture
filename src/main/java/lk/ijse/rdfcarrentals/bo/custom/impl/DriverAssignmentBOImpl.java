package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.DriverAssignmentBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.DriverAssignmentDAOImpl;
import lk.ijse.rdfcarrentals.dto.DriverAssignmentDTO;
import lk.ijse.rdfcarrentals.entity.DriverAssignment;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverAssignmentBOImpl implements DriverAssignmentBO, SuperBO {

    DriverAssignmentDAOImpl driverAssignmentDAO = (DriverAssignmentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER_ASSIGNMENT);

    @Override
    public ArrayList<DriverAssignmentDTO> getAllDriverAssignments() throws SQLException, ClassNotFoundException {
        ArrayList<DriverAssignmentDTO> driverAssignmentDTOS = new ArrayList<>();
        ArrayList<DriverAssignment> driverAssignments = driverAssignmentDAO.getAll();
        
        for (DriverAssignment driverAssignment : driverAssignments) {
            driverAssignmentDTOS.add(new DriverAssignmentDTO(
                    driverAssignment.getLicensePlateNo(),
                    driverAssignment.getDriverNic(),
                    driverAssignment.getPricePerKm(),
                    driverAssignment.getDate(),
                    driverAssignment.getTime()
            ));
        }
        return driverAssignmentDTOS;
    }

    @Override
    public void saveDriverAssignment(DriverAssignmentDTO driverAssignmentDTO) throws SQLException, ClassNotFoundException {
        driverAssignmentDAO.save(new DriverAssignment(
                driverAssignmentDTO.getLicensePlateNo(),
                driverAssignmentDTO.getDriverNic(),
                driverAssignmentDTO.getPricePerKm(),
                driverAssignmentDTO.getDate(),
                driverAssignmentDTO.getTime()
        ));
    }

    @Override
    public boolean saveDriverAssignmentList(ArrayList<DriverAssignmentDTO> driverAssignmentDTOS) throws SQLException, ClassNotFoundException {
        return false;
    }
}
