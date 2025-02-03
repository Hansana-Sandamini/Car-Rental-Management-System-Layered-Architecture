package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.RepairBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.RepairDAOImpl;
import lk.ijse.rdfcarrentals.dto.RepairDTO;
import lk.ijse.rdfcarrentals.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO, SuperBO {

    RepairDAOImpl repairDAO = (RepairDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REPAIR);

    @Override
    public String getNextRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.generateID();
    }

    @Override
    public void saveRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException {
        repairDAO.save(new Repair(
                repairDTO.getRepairId(),
                repairDTO.getDescription(),
                repairDTO.getDate(),
                repairDTO.getCost(),
                repairDTO.getLicensePlateNo()
        ));
    }

    @Override
    public ArrayList<RepairDTO> getAllRepairs() throws SQLException, ClassNotFoundException {
        ArrayList<RepairDTO> repairDTOs = new ArrayList<>();
        ArrayList<Repair> repairs = repairDAO.getAll();

        for (Repair repair : repairs) {
            repairDTOs.add(new RepairDTO(
                    repair.getRepairId(),
                    repair.getDescription(),
                    repair.getDate(),
                    repair.getCost(),
                    repair.getLicensePlateNo()
            ));
        }
        return repairDTOs;
    }

    @Override
    public void deleteRepair(String selectedRepair) throws SQLException, ClassNotFoundException {
        repairDAO.delete(selectedRepair);
    }
}
