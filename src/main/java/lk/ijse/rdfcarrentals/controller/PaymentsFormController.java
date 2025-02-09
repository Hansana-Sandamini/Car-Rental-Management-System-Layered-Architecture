package lk.ijse.rdfcarrentals.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lk.ijse.rdfcarrentals.bo.custom.BOFactory;
import lk.ijse.rdfcarrentals.bo.custom.PaymentBO;
import lk.ijse.rdfcarrentals.bo.custom.ReservationBO;
import lk.ijse.rdfcarrentals.dao.OptionButtonsUtil;
import lk.ijse.rdfcarrentals.dao.ValidationUtil;
import lk.ijse.rdfcarrentals.dto.PaymentDTO;
import lk.ijse.rdfcarrentals.dto.ReservationDTO;
import lk.ijse.rdfcarrentals.entity.Reservation;
import lk.ijse.rdfcarrentals.view.tdm.PaymentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PaymentsFormController implements Initializable {

    @FXML
    private Button btnAddPayment;

    @FXML
    private Button btnRefresh;

    @FXML
    private ComboBox<String> cmbReservationID;

    @FXML
    private TableColumn<PaymentTM, Double> colAmount;

    @FXML
    private TableColumn<PaymentTM, Date> colDate;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentID;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentMethod;

    @FXML
    private TableColumn<PaymentTM, String> colReservationID;

    @FXML
    private TableColumn<PaymentTM, String> colTime;

    @FXML
    private Label lblPaymentID;

    @FXML
    private Label lblBillID;

    @FXML
    private AnchorPane paymentsContent;

    @FXML
    private TableView<PaymentTM> tblPayments;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtFldAmount;

    @FXML
    private TextField txtFldPaymentMethod;

    @FXML
    private TextField txtFldSearchHere;

    @FXML
    private TextField txtTime;

    @FXML
    private Button btnViewBill;

    @FXML
    private FontAwesomeIcon searchIcon;

    private final ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();
//    private final BillModel billModel = new BillModel();

    private static boolean isDarkMode = false;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    @FXML
    void darkModeIconOnAction(MouseEvent event) {
        if (!isDarkMode) {
            paymentsContent.setStyle("-fx-background-color: #293241 ;");
        } else {
            paymentsContent.setStyle("-fx-background-color:  #dfe4ea ;");
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void btnAddPaymentOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (validateTextFields()) {
            try {
                PaymentDTO paymentDTO = getTextFieldsValues();
                paymentBO.savePayment(paymentDTO);
//                billModel.saveBill(new BillDTO(lblBillID.getText(), lblPaymentID.getText(), null, "", LocalDate.now()));
                new Alert(Alert.AlertType.INFORMATION, "Payment Added...!").show();
                refreshPage();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail to Add Payment...!").show();
            }
        }
    }

    PaymentDTO getTextFieldsValues() {
        String paymentID = lblPaymentID.getText();
        String reservationID = cmbReservationID.getValue();
        String billID = lblBillID.getText();
        String paymentMethod = txtFldPaymentMethod.getText();
        Double amount = Double.parseDouble(txtFldAmount.getText());
        Date date = Date.valueOf(txtDate.getValue());
        String time = txtTime.getText();

        return new PaymentDTO(paymentID, reservationID, billID, paymentMethod, amount, date, time);
    }

    boolean validateTextFields() {
        boolean isValidAmount = ValidationUtil.isValidPrice(txtFldAmount);
        boolean isValidTime = ValidationUtil.isValidTime(txtTime);

        return isValidAmount && isValidTime;
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void cmbReservationIDOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedReservationID = cmbReservationID.getSelectionModel().getSelectedItem();
        Reservation reservation = reservationBO.searchReservation(selectedReservationID);

        if (reservation != null) {
            cmbReservationID.setValue(reservation.getReservationId());
        }
    }

    private void loadReservationIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> reservationIDs = reservationBO.getAllReservationIDS();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(reservationIDs);
        cmbReservationID.setItems(observableList);
    }

    @FXML
    void txtFldSearchHereOnAction(KeyEvent event) throws SQLException, ClassNotFoundException {
        String searchText = txtFldSearchHere.getText().toLowerCase();
        ArrayList<PaymentDTO> paymentDTOS = paymentBO.getAllPayments();
        ObservableList<PaymentTM> filteredPayments = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            if (paymentDTO.getPaymentId().toLowerCase().contains(searchText) ||
                    paymentDTO.getPaymentMethod().toLowerCase().contains(searchText) ||
                    paymentDTO.getReservationId().toLowerCase().contains(searchText)) {
                    filteredPayments.add(new PaymentTM(
                        paymentDTO.getPaymentId(),
                        paymentDTO.getReservationId(),
                        paymentDTO.getPaymentMethod(),
                        paymentDTO.getAmount(),
                        paymentDTO.getDate(),
                        paymentDTO.getTime()
                ));
            }
        }
        tblPayments.setItems(filteredPayments);

        if (searchText.isEmpty()) {
            searchIcon.setVisible(true);
        } else {
            searchIcon.setVisible(false);
        }
    }

    @FXML
    void tblPaymentsOnClicked(MouseEvent event) {
        PaymentTM selectedItem = tblPayments.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblPaymentID.setText(selectedItem.getPaymentId());
            cmbReservationID.setValue(selectedItem.getReservationId());
            txtFldPaymentMethod.setText(selectedItem.getPaymentMethod());
            txtFldAmount.setText(String.valueOf(selectedItem.getAmount()));
            txtDate.setValue(selectedItem.getDate().toLocalDate());
            txtTime.setText(selectedItem.getTime());
        }
    }

    @FXML
    void btnViewBillOnAction(ActionEvent event) {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_Date", LocalDate.now().toString());
            parameters.put("p_Bill_Id", lblBillID.getText());

            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/BillPayment.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters
//                    connection
            );
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load Report..!").show();
            e.printStackTrace();
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Data Empty..!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load Report..!").show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        tblPayments.getColumns().get(6).setCellValueFactory(param -> {
            ImageView btnRemove = OptionButtonsUtil.setRemoveButton();

            btnRemove.setOnMouseClicked(event -> {
                PaymentTM selectedPayment = param.getValue();
                tblPayments.getSelectionModel().select(selectedPayment);
                setBtnRemove(event);
            });
            return new ReadOnlyObjectWrapper(new HBox(24, btnRemove));
        });

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBtnRemove(MouseEvent event) {
        String selectedPayment = tblPayments.getSelectionModel().getSelectedItem().getPaymentId();

        if (selectedPayment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Payment?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    paymentBO.deletePayment(selectedPayment);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Payment Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();

                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No payment selected to remove...!").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();
        loadReservationIDs();

        lblPaymentID.setText(paymentBO.getNextPaymentId());
        cmbReservationID.setValue("");
//        lblBillID.setText(billModel.getNextBillId());
        txtFldPaymentMethod.setText("");
        txtFldAmount.setText("");
        txtDate.setValue(null);
        txtTime.setText("");
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOS = paymentBO.getAllPayments();
        paymentTMS.clear();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getPaymentId(),
                    paymentDTO.getReservationId(),
                    paymentDTO.getPaymentMethod(),
                    paymentDTO.getAmount(),
                    paymentDTO.getDate(),
                    paymentDTO.getTime()
            );
            paymentTMS.add(paymentTM);
        }
        tblPayments.setItems(paymentTMS);
    }
}
