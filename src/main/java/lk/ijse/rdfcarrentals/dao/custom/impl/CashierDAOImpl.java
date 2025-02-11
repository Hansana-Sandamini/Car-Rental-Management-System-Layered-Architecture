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
    public boolean save(Cashier cashier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
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
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Cashier search(String selectedCashierUsername) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM cashier WHERE username = ?", selectedCashierUsername);
        if(rst.next()) {
            Cashier cashier = new Cashier(
                    rst.getString("username"),
                    rst.getString("password"),
                    rst.getString("name"),
                    rst.getString("contact_number"),
                    rst.getString("email")
            );
            return cashier;
        }
        return null;
    }

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT username FROM cashier");

        ArrayList<String> cashierUsernames = new ArrayList<>();

        while (rst.next()) {
            cashierUsernames.add(rst.getString(1));
        }
        return cashierUsernames;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Cashier getCashierByUsername(String username) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM cashier WHERE username=?", username);

        if (rst.next()) {
            return new Cashier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }
}
