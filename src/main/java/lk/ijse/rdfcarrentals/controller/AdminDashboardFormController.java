package lk.ijse.rdfcarrentals.controller;

import javafx.application.Platform;
import lk.ijse.rdfcarrentals.util.ClockUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rdfcarrentals.dao.custom.QueryDAO;
import lk.ijse.rdfcarrentals.dao.custom.impl.QueryDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDashboardFormController implements Initializable {

    @FXML
    private AnchorPane adminDashboardContent;

    @FXML
    private AnchorPane adminDashboardHeadingPane;

    @FXML
    private Label lblCreditNotPaid;

    @FXML
    private Label totalSalesViewDetails;

    @FXML
    private Label creditNotPaidViewDetails;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalSales;

    @FXML
    private Label lblRev;

    @FXML
    private Label top1;

    @FXML
    private Label top2;

    @FXML
    private Label top3;

    @FXML
    private BarChart<String, Number> barChart;

    private static boolean isDarkMode = false;

    QueryDAO queryDAO = new QueryDAOImpl();

    @FXML
    void darkModeIconOnAction(MouseEvent event) {
        if (!isDarkMode) {
            adminDashboardContent.setStyle("-fx-background-color: #293241 ;");
        } else {
            adminDashboardContent.setStyle("-fx-background-color:  #dfe4ea ;");
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void creditNotPaidViewDetailsOnAction(MouseEvent event) {
        navigateTo("/view/CreditsForm.fxml");
    }

    @FXML
    void totalSalesViewDetailsOnAction(MouseEvent event) {
        navigateTo("/view/ReservationsForm.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClockUtil.startClock(lblDate, lblTime);
        loadChart();
        int currentYear = Year.now().getValue();

        try {
            lblTotalSales.setText(queryDAO.getMonthlySales() + " Sales");
            lblCreditNotPaid.setText(queryDAO.getCreditNotPaidCount() + " Sales");
            setTopProducts();
            lblRev.setText("Rs " + queryDAO.getYearTotalSaleAmount(currentYear) + ".00");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateTo(String fxmlPath) {
        try {
            adminDashboardContent.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            adminDashboardContent.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load page...!").show();
            e.printStackTrace();
        }
    }

    private void setTopProducts() throws SQLException, ClassNotFoundException {
        ArrayList<String> products = queryDAO.getTopProducts();

        top1.setText(products.get(0));
        top2.setText(products.get(1));
        top3.setText(products.get(2));
    }

    private void loadChart() {
        loadChart(barChart);
    }

    private void loadChart(BarChart barChart) {
        int currentYear = Year.now().getValue();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(String.valueOf(currentYear));

        String[] months = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        try {
            List<Double> monthlyIncome = queryDAO.getIncomeMonthly(currentYear);

            System.out.println("Income data: " + monthlyIncome);
            if (monthlyIncome == null || monthlyIncome.isEmpty()) {
                System.out.println("No income data found for year: " + currentYear);
                return;
            }

            for (int i = 0; i < months.length; i++) {
                series.getData().add(new XYChart.Data<>(months[i], monthlyIncome.get(i)));
            }

            Platform.runLater(() -> {
                if (barChart == null) {
                    System.out.println("Error: Bar chart is NULL!");
                    return;
                }

                barChart.getData().add(series);
                barChart.getXAxis().setTickLabelRotation(0);
            });

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
