package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.FuelTypeBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.FuelTypeDAOImpl;
import lk.ijse.rdfcarrentals.entity.FuelType;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuelTypeBOImpl implements FuelTypeBO, SuperBO {

    FuelTypeDAOImpl fuelTypeDAO = (FuelTypeDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FUEL_TYPE);

    @Override
    public ArrayList<String> loadAllFuelTypeIds() throws SQLException, ClassNotFoundException {
        return fuelTypeDAO.loadAllIDs();
    }

    @Override
    public FuelType searchFuelType(String selectedTypeId) throws SQLException, ClassNotFoundException {
        return fuelTypeDAO.search(selectedTypeId);
    }

}
