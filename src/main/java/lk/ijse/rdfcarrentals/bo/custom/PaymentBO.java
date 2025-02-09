package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO {
    ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;
    void savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    void deletePayment(String selectedPayment) throws SQLException, ClassNotFoundException;
    String getNextPaymentId() throws SQLException, ClassNotFoundException;
}
