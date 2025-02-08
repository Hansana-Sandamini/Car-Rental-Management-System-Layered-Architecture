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
import lk.ijse.rdfcarrentals.bo.custom.CreditBO;
import lk.ijse.rdfcarrentals.bo.custom.CustomerBO;
import lk.ijse.rdfcarrentals.bo.custom.ReservationBO;
import lk.ijse.rdfcarrentals.dao.OptionButtonsUtil;
import lk.ijse.rdfcarrentals.dao.ValidationUtil;
import lk.ijse.rdfcarrentals.dto.CreditDTO;
import lk.ijse.rdfcarrentals.entity.Customer;
import lk.ijse.rdfcarrentals.entity.Reservation;
import lk.ijse.rdfcarrentals.view.tdm.CreditTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CreditsFormController implements Initializable {

    @FXML
    private Label lblBillID;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbCustomerNIC;

    @FXML
    private ComboBox<String> cmbReservationID;

    @FXML
    private TableColumn<CreditTM, Double> colAmountPaid;

    @FXML
    private TableColumn<CreditTM, String> colReservationId;

    @FXML
    private TableColumn<CreditTM, Double> colAmountToPay;

    @FXML
    private TableColumn<CreditTM, String> colCreditID;

    @FXML
    private TableColumn<CreditTM, Date> colDueDate;

    @FXML
    private TableColumn<CreditTM, Double> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private AnchorPane creditsContent;

    @FXML
    private Label lblCreditID;

    @FXML
    private TableView<CreditTM> tblCredits;

    @FXML
    private DatePicker txtDueDate;

    @FXML
    private TextField txtFldAmountPaid;

    @FXML
    private TextField txtFldAmountToPay;

    @FXML
    private TextField txtFldSearchHere;

    @FXML
    private TextField txtFldTotalAmount;

    @FXML
    private FontAwesomeIcon searchIcon;

    @FXML
    private Button btnViewBill;

//    private final BillModel billModel = new BillModel();

    private static boolean isDarkMode = false;

    CreditBO creditBO = (CreditBO) BOFactory.getInstance().getBO(BOFactory.BOType.CREDIT);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    @FXML
    void darkModeIconOnAction(MouseEvent event) {
        if (!isDarkMode) {
            creditsContent.setStyle("-fx-background-color: #293241 ;");
        } else {
            creditsContent.setStyle("-fx-background-color:  #dfe4ea ;");
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        if (validateTextFields()) {
            try {
                CreditDTO creditDTO = getTextFieldsValues();
                creditBO.saveCredit(creditDTO);
//                billModel.saveBill(new BillDTO(lblBillID.getText(), null, lblCreditID.getText(), "", LocalDate.now()));
                new Alert(Alert.AlertType.INFORMATION, "Credit Saved...!").show();
                refreshPage();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail to Save Credit...!").show();
            }
        }
    }

    CreditDTO getTextFieldsValues() {
        String creditID = lblCreditID.getText();
        String reservationId = cmbReservationID.getValue();
        double totalAmount = Double.parseDouble(txtFldTotalAmount.getText());
        double amountPaid = Double.parseDouble(txtFldAmountPaid.getText());
        double amountToPay = Double.parseDouble(txtFldAmountToPay.getText());
        Date dueDate = Date.valueOf(txtDueDate.getValue());

        return new CreditDTO(creditID, reservationId, totalAmount, amountPaid, amountToPay, dueDate);
    }

    boolean validateTextFields() {
        boolean isValidTotalAmount = ValidationUtil.isValidPrice(txtFldTotalAmount);
        boolean isValidAmountPaid = ValidationUtil.isValidPrice(txtFldAmountPaid);
        boolean isValidAmountToPay = ValidationUtil.isValidPrice(txtFldAmountToPay);

        return isValidTotalAmount && isValidAmountPaid && isValidAmountToPay;
    }

    @FXML
    void cmbCustomerNICOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedCustomerNIC = cmbCustomerNIC.getSelectionModel().getSelectedItem();
        Customer customer = customerBO.searchCustomer(selectedCustomerNIC);

        if (customer != null) {
            cmbCustomerNIC.setValue(customer.getNic());
        }
    }

    private void loadCustomerNICs() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerNICs = customerBO.getAllCustomerNICs();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerNICs);
        cmbCustomerNIC.setItems(observableList);
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
    void btnViewBillOnAction(ActionEvent event) {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_Date", LocalDate.now().toString());
            //parameters.put("p_credit_id", lblCreditID.getText());
            parameters.put("p_Bill_Id", lblBillID.getText());

            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/BillCredits.jrxml"));
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

    @FXML
    void txtFldSearchHereOnAction(KeyEvent event) throws SQLException, ClassNotFoundException {
        String searchText = txtFldSearchHere.getText().toLowerCase();
        ArrayList<CreditDTO> creditDTOS = creditBO.getAllCredits();
        ObservableList<CreditTM> filteredCredits = FXCollections.observableArrayList();

        for (CreditDTO creditDTO : creditDTOS) {
            if (creditDTO.getCreditId().toLowerCase().contains(searchText) ||
                creditDTO.getReservationId().toLowerCase().contains(searchText)) {
                filteredCredits.add(new CreditTM(
                        creditDTO.getCreditId(),
                        creditDTO.getReservationId(),
                        creditDTO.getTotalAmount(),
                        creditDTO.getAmountPaid(),
                        creditDTO.getAmountToPay(),
                        creditDTO.getDueDate()
                ));
            }
        }
        tblCredits.setItems(filteredCredits);

        if (searchText.isEmpty()) {
            searchIcon.setVisible(true);
        } else {
            searchIcon.setVisible(false);
        }
    }

    @FXML
    void tblCreditsOnClicked(MouseEvent event) {
        CreditTM selectedItem = tblCredits.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblCreditID.setText(selectedItem.getCreditId());
            cmbReservationID.setValue(selectedItem.getReservationId());
            txtFldTotalAmount.setText(Double.toString(selectedItem.getTotalAmount()));
            txtFldAmountPaid.setText(Double.toString(selectedItem.getAmountPaid()));
            txtFldAmountToPay.setText(Double.toString(selectedItem.getAmountToPay()));
            txtDueDate.setValue(LocalDate.parse(LocalDate.now().toString()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCreditID.setCellValueFactory(new PropertyValueFactory<>("creditId"));
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        colAmountToPay.setCellValueFactory(new PropertyValueFactory<>("amountToPay"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        addAutoCalculationListeners();

        tblCredits.getColumns().get(6).setCellValueFactory(param -> {
            ImageView btnRemove = OptionButtonsUtil.setRemoveButton();
            ImageView btnUpdate = OptionButtonsUtil.setUpdateButton();

            btnRemove.setOnMouseClicked(event -> {
                CreditTM selectedCredit = param.getValue();
                tblCredits.getSelectionModel().select(selectedCredit);
                setBtnRemove(event);
            });

            btnUpdate.setOnMouseClicked(event -> {
                CreditTM selectedCredit = param.getValue();
                tblCredits.getSelectionModel().select(selectedCredit);
                try {
                    setBtnUpdate(event);
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            });

            return new ReadOnlyObjectWrapper(new HBox(24, btnUpdate, btnRemove));
        });

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAutoCalculationListeners() {
        txtFldTotalAmount.textProperty().addListener((observable, oldValue, newValue) -> calculateAmountToPay());
        txtFldAmountPaid.textProperty().addListener((observable, oldValue, newValue) -> calculateAmountToPay());
    }

    private void calculateAmountToPay() {
        try {
            double totalAmount = Double.parseDouble(txtFldTotalAmount.getText());
            double amountPaid = Double.parseDouble(txtFldAmountPaid.getText());
            double amountToPay = totalAmount - amountPaid;

            if (amountToPay < 0) {
                new Alert(Alert.AlertType.WARNING, "Amount Paid cannot exceed Total Amount!").show();
                txtFldAmountPaid.setText("0");
                txtFldAmountToPay.setText(Double.toString(totalAmount));
            } else {
                txtFldAmountToPay.setText(Double.toString(amountToPay));
            }
        } catch (NumberFormatException e) {
            txtFldAmountToPay.setText("0.00");
        }
    }

    private void setBtnRemove(MouseEvent event) {
        String selectedCredit = tblCredits.getSelectionModel().getSelectedItem().getCreditId();

        if (selectedCredit != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Credit?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    creditBO.deleteCredit(selectedCredit);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Credit Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();

                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Credit selected to Remove...!").show();
        }
    }

    private void setBtnUpdate(MouseEvent event) throws SQLException, ClassNotFoundException {
        String  selectedCredit = tblCredits.getSelectionModel().getSelectedItem().getCreditId();

        if (selectedCredit != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this Credit?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {

                if (validateTextFields()) {
                    try {
                        CreditDTO creditDTO = getTextFieldsValues();
                        creditBO.updateCredit(creditDTO);
                        new Alert(Alert.AlertType.INFORMATION, "Credit Updated...!").show();
                        refreshPage();

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Fail to Update Credit...!").show();
                    }
                }
            } else {
                refreshPage();
            }
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();
        loadCustomerNICs();
        loadReservationIDs();

        lblCreditID.setText(creditBO.getNextCreditId());
        cmbCustomerNIC.setValue("");
        cmbReservationID.setValue("");
        txtFldTotalAmount.setText("");
        txtFldAmountPaid.setText("");
        txtFldAmountToPay.setText("");
        txtDueDate.setValue(null);
//        lblBillID.setText(billModel.getNextBillId());
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<CreditDTO> creditDTOS = creditBO.getAllCredits();
        ObservableList<CreditTM> creditTMS = FXCollections.observableArrayList();

        for (CreditDTO creditDTO : creditDTOS) {
            CreditTM creditTM = new CreditTM(
                    creditDTO.getCreditId(),
                    creditDTO.getReservationId(),
                    creditDTO.getTotalAmount(),
                    creditDTO.getAmountPaid(),
                    creditDTO.getAmountToPay(),
                    creditDTO.getDueDate()
            );
            creditTMS.add(creditTM);
        }
        tblCredits.setItems(creditTMS);
    }

}