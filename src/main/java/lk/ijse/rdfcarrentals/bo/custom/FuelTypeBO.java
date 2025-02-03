package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.entity.FuelType;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FuelTypeBO {
    ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException;
    FuelType search(String selectedTypeId) throws SQLException, ClassNotFoundException;
}
