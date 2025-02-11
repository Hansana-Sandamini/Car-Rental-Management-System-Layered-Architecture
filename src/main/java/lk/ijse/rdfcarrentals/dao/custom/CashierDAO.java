package lk.ijse.rdfcarrentals.dao.custom;

import lk.ijse.rdfcarrentals.dao.CrudDAO;
import lk.ijse.rdfcarrentals.entity.Cashier;

import java.sql.SQLException;

public interface CashierDAO extends CrudDAO<Cashier> {
    Cashier getCashierByUsername(String username) throws SQLException, ClassNotFoundException;
}
