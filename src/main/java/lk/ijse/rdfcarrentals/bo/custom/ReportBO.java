package lk.ijse.rdfcarrentals.bo.custom;

import net.sf.jasperreports.engine.JasperPrint;

import java.sql.SQLException;
import java.util.Map;

public interface ReportBO {
    JasperPrint generateReport(String reportPath, Map<String, Object> parameters) throws SQLException, ClassNotFoundException;
}
