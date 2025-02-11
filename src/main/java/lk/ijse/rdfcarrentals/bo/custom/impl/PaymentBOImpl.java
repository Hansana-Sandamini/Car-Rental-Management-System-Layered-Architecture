package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.PaymentBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.rdfcarrentals.dto.PaymentDTO;
import lk.ijse.rdfcarrentals.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO, SuperBO {

    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        ArrayList<Payment> payments = paymentDAO.getAll();
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(
                    payment.getPaymentId(),
                    payment.getReservationId(),
                    payment.getPaymentMethod(),
                    payment.getAmount(),
                    payment.getDate(),
                    payment.getTime()
            ));
        }
        return paymentDTOS;
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getReservationId(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getAmount(),
                paymentDTO.getDate(),
                paymentDTO.getTime()
        ));
    }

    @Override
    public void deletePayment(String selectedPayment) throws SQLException, ClassNotFoundException {
        paymentDAO.delete(selectedPayment);
    }

    @Override
    public String getNextPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateID();
    }
}
