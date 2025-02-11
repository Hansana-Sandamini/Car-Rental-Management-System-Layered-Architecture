package lk.ijse.rdfcarrentals.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.rdfcarrentals.bo.custom.ReportBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.db.DBConnection;
import net.sf.jasperreports.engine.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class ReportBOImpl implements ReportBO, SuperBO {

    @Override
    public JasperPrint generateReport(String reportPath, Map<String, Object> parameters) throws SQLException, ClassNotFoundException {
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
