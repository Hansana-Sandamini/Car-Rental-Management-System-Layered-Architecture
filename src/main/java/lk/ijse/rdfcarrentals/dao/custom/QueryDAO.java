package lk.ijse.rdfcarrentals.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.rdfcarrentals.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    ObservableList<Double> getIncomeMonthly() throws SQLException, ClassNotFoundException;
}
