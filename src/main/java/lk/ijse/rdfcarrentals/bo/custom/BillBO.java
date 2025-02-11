package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.BillDTO;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.SQLException;
import java.util.Map;

public interface BillBO {
    String getNextBillId() throws SQLException, ClassNotFoundException;
    void saveBill(BillDTO billDTO) throws SQLException, ClassNotFoundException;
    JasperPrint generateBill(String reportPath, Map<String, Object> parameters) throws SQLException, ClassNotFoundException;
}
