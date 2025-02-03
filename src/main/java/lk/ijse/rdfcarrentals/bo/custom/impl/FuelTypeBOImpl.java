package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.FuelTypeBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.FuelTypeDAOImpl;
import lk.ijse.rdfcarrentals.entity.FuelType;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuelTypeBOImpl implements FuelTypeBO, SuperBO {

    FuelTypeDAOImpl fuelTypeDAO = (FuelTypeDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FUELTYPE);

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        return fuelTypeDAO.loadAllIDs();
    }

    @Override
    public FuelType search(String selectedTypeId) throws SQLException, ClassNotFoundException {
        return fuelTypeDAO.search(selectedTypeId);
    }
}
