package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.BillDAO;
import lk.ijse.rdfcarrentals.entity.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillDAOImpl implements BillDAO {

    @Override
    public ArrayList<Bill> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void save(Bill bill) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO bill VALUES (?,?,?,?,?)",
                bill.getBillId(),
                bill.getPaymentId(),
                bill.getCreditId(),
                bill.getDescription(),
                bill.getIssueDate()
        );
    }

    @Override
    public void update(Bill bill) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT bill_id FROM bill ORDER BY bill_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("B%03d", newIdIndex);
        }
        return "B001";
    }

    @Override
    public Bill search(String id) throws SQLException, ClassNotFoundException {
        return null;
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
