package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.CashierDAO;
import lk.ijse.rdfcarrentals.entity.Cashier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CashierDAOImpl implements CashierDAO {

    @Override
    public ArrayList<Cashier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM cashier");

        ArrayList<Cashier> cashiers = new ArrayList<>();

        while (rst.next()) {
            Cashier cashier = new Cashier();
            cashier.setUserName(rst.getString("username"));
            cashier.setPassword(rst.getString("password"));
            cashier.setName(rst.getString("name"));
            cashier.setContactNumber(rst.getString("contact_number"));
            cashier.setEmail(rst.getString("email"));
            cashiers.add(cashier);
        }
        return cashiers;
    }

    @Override
    public void save(Cashier cashier) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO cashier VALUES (?,?,?,?,?)",
                cashier.getUserName(),
                cashier.getPassword(),
                cashier.getName(),
                cashier.getContactNumber(),
                cashier.getEmail()
        );
    }

    @Override
    public void update(Cashier cashier) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "UPDATE cashier SET password = ?, name = ?, contact_number = ?, email = ? WHERE username = ?",
                cashier.getPassword(),
                cashier.getName(),
                cashier.getContactNumber(),
                cashier.getEmail(),
                cashier.getUserName()
        );
    }

    @Override
    public void delete(String selectedCashier) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM cashier WHERE username = ?", selectedCashier);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Cashier search(String selectedCashierUsername) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM cashier WHERE username = ?", selectedCashierUsername);
        rst.next();
        Cashier cashier = new Cashier(
                rst.getString("username"),
                rst.getString("password"),
                rst.getString("name"),
                rst.getString("contact_number"),
                rst.getString("email")
        );
        return cashier;
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
