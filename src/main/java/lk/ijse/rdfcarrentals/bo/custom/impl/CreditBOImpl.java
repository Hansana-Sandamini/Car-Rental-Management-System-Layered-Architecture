package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.CreditBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.CreditDAOImpl;
import lk.ijse.rdfcarrentals.dto.CreditDTO;
import lk.ijse.rdfcarrentals.entity.Credit;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreditBOImpl implements CreditBO, SuperBO {

    CreditDAOImpl creditDAO = (CreditDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CREDIT);

    @Override
    public String getNextCreditId() throws SQLException, ClassNotFoundException {
        return creditDAO.generateID();
    }

    @Override
    public ArrayList<CreditDTO> getAllCredits() throws SQLException, ClassNotFoundException {
        ArrayList<CreditDTO> creditDTOS = new ArrayList<>();
        ArrayList<Credit> credits = creditDAO.getAll();

        for (Credit credit : credits) {
            creditDTOS.add(new CreditDTO(
                    credit.getCreditId(),
                    credit.getReservationId(),
                    credit.getTotalAmount(),
                    credit.getAmountPaid(),
                    credit.getAmountToPay(),
                    credit.getDueDate()
            ));
        }
        return creditDTOS;
    }

    @Override
    public boolean saveCredit(CreditDTO creditDTO) throws SQLException, ClassNotFoundException {
        return creditDAO.save(new Credit(
                creditDTO.getCreditId(),
                creditDTO.getReservationId(),
                creditDTO.getTotalAmount(),
                creditDTO.getAmountPaid(),
                creditDTO.getAmountToPay(),
                creditDTO.getDueDate()
        ));
    }

    @Override
    public void updateCredit(CreditDTO creditDTO) throws SQLException, ClassNotFoundException {
        creditDAO.update(new Credit(
                creditDTO.getCreditId(),
                creditDTO.getReservationId(),
                creditDTO.getTotalAmount(),
                creditDTO.getAmountPaid(),
                creditDTO.getAmountToPay(),
                creditDTO.getDueDate()
        ));
    }

    @Override
    public void deleteCredit(String selectedCredit) throws SQLException, ClassNotFoundException {
        creditDAO.delete(selectedCredit);
    }
}
