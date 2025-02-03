package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.FuelTypeDTO;
import lk.ijse.rdfcarrentals.entity.FuelType;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FuelTypeBO {
    ArrayList<String> loadAllFuelTypeIds() throws SQLException, ClassNotFoundException;
    FuelType searchFuelType(String selectedTypeId) throws SQLException, ClassNotFoundException;
}
