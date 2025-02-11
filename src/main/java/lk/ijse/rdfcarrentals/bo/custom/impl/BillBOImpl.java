package lk.ijse.rdfcarrentals.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.rdfcarrentals.bo.custom.BillBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.BillDAO;
import lk.ijse.rdfcarrentals.db.DBConnection;
import lk.ijse.rdfcarrentals.dto.BillDTO;
import lk.ijse.rdfcarrentals.entity.Bill;
import net.sf.jasperreports.engine.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class BillBOImpl implements BillBO, SuperBO {

    BillDAO billDAO = (BillDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BILL);

    @Override
    public String getNextBillId() throws SQLException, ClassNotFoundException {
        return billDAO.generateID();
    }

    @Override
    public void saveBill(BillDTO billDTO) throws SQLException, ClassNotFoundException {
        billDAO.save(new Bill(
                billDTO.getBillId(),
                billDTO.getPaymentId(),
                billDTO.getCreditId(),
                billDTO.getDescription(),
                billDTO.getIssueDate()
        ));
    }

    @Override
    public JasperPrint generateBill(String reportPath, Map<String, Object> parameters) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream(reportPath));
            return JasperFillManager.fillReport(jasperReport, parameters, connection);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load Report..!").show();
        }
        return null;
    }
}
