package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.CashierDTO;
import lk.ijse.rdfcarrentals.entity.Cashier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierBO {
    ArrayList<CashierDTO> getAllCashiers() throws SQLException, ClassNotFoundException;
    void saveCashier(CashierDTO cashierDTO) throws SQLException, ClassNotFoundException;
    void updateCashier(CashierDTO cashierDTO) throws SQLException, ClassNotFoundException;
    Cashier searchCashier(String selectedCashierUsername) throws SQLException, ClassNotFoundException;
    void deleteCashier(String selectedCashier) throws SQLException, ClassNotFoundException;
    ArrayList<String> loadAllCashierUsernames() throws SQLException, ClassNotFoundException;
}
