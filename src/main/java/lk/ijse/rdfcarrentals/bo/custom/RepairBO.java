package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.RepairDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairBO {
    String getNextRepairId() throws SQLException, ClassNotFoundException;
    void saveRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException;
    ArrayList<RepairDTO> getAllRepairs() throws SQLException, ClassNotFoundException;
    void deleteRepair(String selectedRepair) throws SQLException, ClassNotFoundException;
}
