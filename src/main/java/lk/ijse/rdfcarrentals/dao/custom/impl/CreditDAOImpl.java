package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.CreditDAO;
import lk.ijse.rdfcarrentals.entity.Credit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditDAOImpl implements CreditDAO {
    @Override
    public ArrayList<Credit> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM credit");

        ArrayList<Credit> credits = new ArrayList<>();

        while (rst.next()) {
            Credit credit = new Credit();
            credit.setCreditId(rst.getString("credit_id"));
            credit.setReservationId(rst.getString("reservation_id"));
            credit.setTotalAmount(rst.getDouble("total_amount"));
            credit.setAmountPaid(rst.getDouble("amount_paid"));
            credit.setAmountToPay(rst.getDouble("amount_to_pay"));
            credit.setDueDate(rst.getDate("due_date"));
            credits.add(credit);
        }
        return credits;
    }

    @Override
    public void save(Credit credit) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO credit VALUES (?,?,?,?,?,?)",
                credit.getCreditId(),
                credit.getReservationId(),
                credit.getTotalAmount(),
                credit.getAmountPaid(),
                credit.getAmountToPay(),
                credit.getDueDate()
        );
    }

    @Override
    public void update(Credit credit) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "UPDATE credit SET reservation_id = ?, total_amount = ?, amount_paid = ?, amount_to_pay = ?, due_date = ? WHERE credit_id = ?",
                credit.getReservationId(),
                credit.getTotalAmount(),
                credit.getAmountPaid(),
                credit.getAmountToPay(),
                credit.getDueDate(),
                credit.getCreditId()
        );
    }

    @Override
    public void delete(String selectedCredit) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM credit WHERE credit_id = ?", selectedCredit);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT credit_id FROM credit ORDER BY credit_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("C%03d", newIdIndex);
        }
        return "C001";
    }

    @Override
    public Credit search(String id) throws SQLException, ClassNotFoundException {
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
