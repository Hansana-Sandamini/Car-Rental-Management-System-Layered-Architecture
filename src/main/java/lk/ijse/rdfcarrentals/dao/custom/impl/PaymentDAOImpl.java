package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.PaymentDAO;
import lk.ijse.rdfcarrentals.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");

        ArrayList<Payment> payments = new ArrayList<>();
        while (rst.next()) {
            Payment payment = new Payment();
            payment.setPaymentId(rst.getString("payment_id"));
            payment.setReservationId(rst.getString("reservation_id"));
            payment.setPaymentMethod(rst.getString("payment_method"));
            payment.setAmount(rst.getDouble("amount"));
            payment.setDate(rst.getDate("date"));
            payment.setTime(rst.getString("time"));
            payments.add(payment);
        }
        return payments;
    }

    @Override
    public boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO payment VALUES (?,?,?,?,?,?)",
                payment.getPaymentId(),
                payment.getReservationId(),
                payment.getPaymentMethod(),
                payment.getAmount(),
                payment.getDate(),
                payment.getTime()
        );
    }

    @Override
    public void update(Payment payment) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void delete(String selectedPayment) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM payment WHERE payment_id = ?", selectedPayment);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
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
