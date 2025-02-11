package lk.ijse.rdfcarrentals.controller;

import javafx.scene.control.Alert;
import lk.ijse.rdfcarrentals.bo.custom.BOFactory;
import lk.ijse.rdfcarrentals.bo.custom.ReportBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReportsFormController implements Initializable {

    @FXML
    private Button btnDurationReportGenerate;

    @FXML
    private Button btnMonthOverviewGenerate;

    @FXML
    private Button btnMonthReportGenerate;

    @FXML
    private Button btnYearOverviewGenerate;

    @FXML
    private Button btnYearReportGenerate;

    @FXML
    private AnchorPane reportsContent;

    @FXML
    private DatePicker txtFrom;

    @FXML
    private DatePicker txtTo;

    private static boolean isDarkMode = false;

    ReportBO reportBO = (ReportBO) BOFactory.getInstance().getBO(BOFactory.BOType.REPORT);

    @FXML
    void darkModeIconOnAction(MouseEvent event) {
        if (!isDarkMode) {
            reportsContent.setStyle("-fx-background-color: #293241 ;");
        } else {
            reportsContent.setStyle("-fx-background-color:  #dfe4ea ;");
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void btnDurationReportGenerateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        generateReport("/reports/DurationReport.jrxml");
    }

    @FXML
    void btnMonthOverviewGenerateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        generateReport("/reports/MonthOverview.jrxml");
    }

    @FXML
    void btnMonthReportGenerateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        generateReport("/reports/MonthReport.jrxml");
    }

    @FXML
    void btnYearOverviewGenerateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        generateReport("/reports/YearOverview.jrxml");
    }

    @FXML
    void btnYearReportGenerateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        generateReport("/reports/YearReport.jrxml");
    }

    private void generateReport(String reportPath) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_Date", LocalDate.now().toString());
            if (txtFrom.getValue() != null) parameters.put("p_txtFrom", txtFrom.getValue().toString());
            if (txtTo.getValue() != null) parameters.put("p_txtTo", txtTo.getValue().toString());

            JasperPrint jasperPrint = reportBO.generateReport(reportPath, parameters);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate report!").show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
