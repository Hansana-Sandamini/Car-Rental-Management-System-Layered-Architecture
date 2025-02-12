package lk.ijse.rdfcarrentals.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.rdfcarrentals.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ObservableList<Double> getIncomeMonthly(int year) throws SQLException, ClassNotFoundException;
    ArrayList<String> getTopProducts() throws SQLException, ClassNotFoundException;
    int getYearTotalSaleAmount(int year) throws SQLException, ClassNotFoundException;
    int getMonthlySales() throws SQLException, ClassNotFoundException;
    int getCreditNotPaidCount() throws SQLException, ClassNotFoundException;
}
