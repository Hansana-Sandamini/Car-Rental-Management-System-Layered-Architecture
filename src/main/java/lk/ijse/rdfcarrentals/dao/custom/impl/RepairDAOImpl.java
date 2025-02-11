package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.RepairDAO;
import lk.ijse.rdfcarrentals.entity.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOImpl implements RepairDAO {

    @Override
    public ArrayList<Repair> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM repair");

        ArrayList<Repair> repairs = new ArrayList<>();

        while (rst.next()) {
            Repair repair = new Repair();
            repair.setRepairId(rst.getString("repair_id"));
            repair.setDescription(rst.getString("description"));
            repair.setDate(rst.getDate("date"));
            repair.setCost(rst.getDouble("cost"));
            repair.setLicensePlateNo(rst.getString("license_plate_no"));
            repairs.add(repair);
        }
        return repairs;
    }

    @Override
    public boolean save(Repair repair) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO repair VALUES (?,?,?,?,?)",
                repair.getRepairId(),
                repair.getDescription(),
                repair.getDate(),
                repair.getCost(),
                repair.getLicensePlateNo()
        );
    }

    @Override
    public void update(Repair repair) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void delete(String selectedRepair) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM repair WHERE repair_id = ?", selectedRepair);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT repair_id FROM repair ORDER BY repair_id DESC LIMIT 1");

        if (rst.next()) {
            String repairId = rst.getString("repair_id");
            int newRepairId = Integer.parseInt(repairId.replace("Rep-", "")) + 1;
            return String.format("Rep-%03d", newRepairId);
        } else {
            return "Rep-001";
        }
    }

    @Override
    public Repair search(String id) throws SQLException, ClassNotFoundException {
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
