package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.BillDTO;

import java.sql.SQLException;

public interface BillBO {
    String getNextBillId() throws SQLException, ClassNotFoundException;
    void saveBill(BillDTO billDTO) throws SQLException, ClassNotFoundException;
}
