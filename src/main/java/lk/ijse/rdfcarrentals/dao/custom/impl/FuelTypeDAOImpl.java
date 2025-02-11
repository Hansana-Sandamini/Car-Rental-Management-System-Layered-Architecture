package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.FuelTypeDAO;
import lk.ijse.rdfcarrentals.entity.FuelType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuelTypeDAOImpl implements FuelTypeDAO {

    @Override
    public ArrayList<FuelType> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(FuelType cashierDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(FuelType cashierDTO) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public FuelType search(String selectedTypeId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM fuel_type WHERE type_id = ?", selectedTypeId);
        if (rst.next()) {
            FuelType fuelType = new FuelType(
                    rst.getString(1),
                    rst.getString(2)
            );
            return fuelType;
        }
        return null;
    }

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT type_id FROM fuel_type");

        ArrayList<String> fuelTypeIds = new ArrayList<>();

        while (rst.next()) {
            fuelTypeIds.add(rst.getString(1));
        }
        return fuelTypeIds;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
