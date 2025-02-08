package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.CreditDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CreditBO {
    String getNextCreditId() throws SQLException, ClassNotFoundException;
    ArrayList<CreditDTO> getAllCredits() throws SQLException, ClassNotFoundException;
    void saveCredit(CreditDTO creditDTO) throws SQLException, ClassNotFoundException;
    void updateCredit(CreditDTO creditDTO) throws SQLException, ClassNotFoundException;
    void deleteCredit(String selectedCredit) throws SQLException, ClassNotFoundException;
}
