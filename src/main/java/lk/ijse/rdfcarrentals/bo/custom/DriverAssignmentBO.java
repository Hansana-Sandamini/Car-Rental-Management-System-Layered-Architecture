package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.DriverAssignmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverAssignmentBO {
    ArrayList<DriverAssignmentDTO> getAllDriverAssignments() throws SQLException, ClassNotFoundException;
    void saveDriverAssignment(DriverAssignmentDTO driverAssignmentDTO) throws SQLException, ClassNotFoundException;
    boolean saveDriverAssignmentList(ArrayList<DriverAssignmentDTO> driverAssignmentDTOS) throws SQLException, ClassNotFoundException;
}
