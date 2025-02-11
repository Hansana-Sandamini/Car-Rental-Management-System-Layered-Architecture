package lk.ijse.rdfcarrentals.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.rdfcarrentals.bo.custom.*;
import lk.ijse.rdfcarrentals.dao.OptionButtonsUtil;
import lk.ijse.rdfcarrentals.dao.ValidationUtil;
import lk.ijse.rdfcarrentals.dto.*;
import lk.ijse.rdfcarrentals.entity.*;
import lk.ijse.rdfcarrentals.view.tdm.ReservationTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ReservationsFormController implements Initializable {

    @FXML
    private AnchorPane reservationHeadingPane;

    @FXML
    private Button btnAddReservation;

    @FXML
    private Button btnViewReservationDetails;

    @FXML
    private ComboBox<String> cmbCashierUserName;

    @FXML
    private ComboBox<String> cmbCustomerNIC;

    @FXML
    private ComboBox<String> cmbIsDriverWant;

    @FXML
    private ComboBox<String> cmbLicensePlateNo;

    @FXML
    private TableColumn<ReservationTM, String> colCashierUserName;

    @FXML
    private TableColumn<ReservationTM, String> colCustomerNIC;

    @FXML
    private TableColumn<ReservationTM, String> colIsDriverWant;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private TableColumn<ReservationTM, Date> colPickUpDate;

    @FXML
    private TableColumn<ReservationTM, String> colPickUpTime;

    @FXML
    private TableColumn<ReservationTM, String> colReservationID;

    @FXML
    private TableColumn<ReservationTM, Date> colReturnDate;

    @FXML
    private TableColumn<ReservationTM, String> colReturnTime;

    @FXML
    private Label lblReservationID;

    @FXML
    private AnchorPane reservationsContent;

    @FXML
    private TableView<ReservationTM> tblReservations;

    @FXML
    private TextField txtFldSearchHere;

    @FXML
    private DatePicker txtPickUpDate;

    @FXML
    private DatePicker txtReturnDate;

    @FXML
    private TextField txtPickUpTime;

    @FXML
    private TextField txtReturnTime;

    @FXML
    private TextField txtDriverCost;

    @FXML
    private TextField txtTotalAmount;

    @FXML
    private AnchorPane reservationFormPane;

    @FXML
    private FontAwesomeIcon searchIcon;

    @FXML
    private Label lblHeadingUserName;

    private static boolean isDarkMode = false;

    private final ObservableList<ReservationTM> reservationTMS = FXCollections.observableArrayList();

    ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    CarBO carBO = (CarBO) BOFactory.getInstance().getBO(BOFactory.BOType.CAR);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    CashierBO cashierBO = (CashierBO) BOFactory.getInstance().getBO(BOFactory.BOType.CASHIER);

    @FXML
    void darkModeIconOnAction(MouseEvent event) {
        if (!isDarkMode) {
            reservationsContent.setStyle("-fx-background-color: #293241 ;");
        } else {
            reservationsContent.setStyle("-fx-background-color:  #dfe4ea ;");
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void btnAddReservationOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (validateTextFields()) {
            ReservationDTO reservationDTO = getTextFieldsValues();

            boolean isAdded = reservationBO.saveReservation(reservationDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Reservation Added...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Add Reservation...!").show();
            }
        }
    }

    ReservationDTO getTextFieldsValues() {
        String reservationId = lblReservationID.getText();
        String customerNic = cmbCustomerNIC.getValue();
        String cashierUsername = cmbCashierUserName.getValue();
        String licensePlateNo = cmbLicensePlateNo.getValue();
        Date pickUpDate = Date.valueOf(txtPickUpDate.getValue());
        String pickUpTime = txtPickUpTime.getText();
        Date returnDate = Date.valueOf(txtReturnDate.getValue());
        String returnTime = txtReturnTime.getText();
        String isDriverWant = cmbIsDriverWant.getValue();
        Double driverCost = Double.valueOf(txtDriverCost.getText());
        Double totalAmount = Double.valueOf(txtTotalAmount.getText());

        ReservationDetail reservationDetail = new ReservationDetail(
                reservationId, licensePlateNo, driverCost, totalAmount
        );
        ArrayList<ReservationDetail> reservationDetails = new ArrayList<>(Collections.singletonList(reservationDetail));

        return new ReservationDTO(reservationId, customerNic, cashierUsername, pickUpDate, pickUpTime, returnDate, returnTime, isDriverWant, reservationDetails);
    }

    boolean validateTextFields() {
        boolean isValidDriverCost = ValidationUtil.isValidPrice(txtDriverCost);
        boolean isValidTotalAmount = ValidationUtil.isValidPrice(txtTotalAmount);
        boolean isValidPickUpTime = ValidationUtil.isValidTime(txtPickUpTime);
        boolean isValidReturnTime = ValidationUtil.isValidTime(txtReturnTime);

        return isValidDriverCost && isValidTotalAmount && isValidPickUpTime && isValidReturnTime;
    }

    @FXML
    void btnViewReservationDetailsOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReservationDetailForm.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load page...!").show();
        }
    }

    @FXML
    void cmbCashierUserNameOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedCashierUsername = cmbCashierUserName.getSelectionModel().getSelectedItem();
        Cashier cashier = cashierBO.searchCashier(selectedCashierUsername);

        if (cashier != null) {
            cmbCashierUserName.setValue(cashier.getUserName());
        }
    }

    private void loadCashierUsernames() throws SQLException, ClassNotFoundException {
        ArrayList<String> cashierUsernames = cashierBO.loadAllCashierUsernames();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(cashierUsernames);
        cmbCashierUserName.setItems(observableList);
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
    void cmbLicensePlateNoOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedLicensePlateNo = cmbLicensePlateNo.getSelectionModel().getSelectedItem();
        Car car = carBO.searchCar(selectedLicensePlateNo);
    }

    private void loadLicensePlateNos() throws SQLException, ClassNotFoundException {
        ArrayList<String> licensePlateNos = carBO.loadAllLicensePlateNos();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(licensePlateNos);
        cmbLicensePlateNo.setItems(observableList);
    }

    @FXML
    void txtFldSearchHereOnAction(KeyEvent event) throws SQLException, ClassNotFoundException {
        String searchText = txtFldSearchHere.getText().toLowerCase();
        ArrayList<ReservationDTO> reservationDTOS = reservationBO.getAllReservations();
        ObservableList<ReservationTM> filteredReservations = FXCollections.observableArrayList();

        for (ReservationDTO reservationDTO : reservationDTOS) {
            if (reservationDTO.getReservationId().toLowerCase().contains(searchText) ||
                    reservationDTO.getCustomerNic().toLowerCase().contains(searchText) ||
                    reservationDTO.getCashierUsername().toLowerCase().contains(searchText)) {
                    filteredReservations.add(new ReservationTM(
                            reservationDTO.getReservationId(),
                            reservationDTO.getCustomerNic(),
                            reservationDTO.getCashierUsername(),
                            reservationDTO.getPickUpDate(),
                            reservationDTO.getPickUpTime(),
                            reservationDTO.getReturnDate(),
                            reservationDTO.getReturnTime(),
                            reservationDTO.getIsDriverWant()
                    ));
            }
        }
        tblReservations.setItems(filteredReservations);

        if (searchText.isEmpty()) {
            searchIcon.setVisible(true);
        } else {
            searchIcon.setVisible(false);
        }
    }

    @FXML
    void tblReservationsOnClicked(MouseEvent event) {
        ReservationTM selectedItem = tblReservations.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblReservationID.setText(selectedItem.getReservationId());
            cmbCustomerNIC.setValue(selectedItem.getCustomerNic());
            cmbCashierUserName.setValue(selectedItem.getCashierUsername());
            txtPickUpDate.setValue(LocalDate.parse(LocalDate.now().toString()));
            txtPickUpTime.setText(selectedItem.getPickUpTime().toString());
            txtReturnDate.setValue(LocalDate.parse(LocalDate.now().toString()));
            txtReturnTime.setText(selectedItem.getReturnTime().toString());
            cmbIsDriverWant.setValue(selectedItem.getIsDriverWant());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbIsDriverWant.getItems().addAll("Yes", "No");

        colReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colCustomerNIC.setCellValueFactory(new PropertyValueFactory<>("customerNic"));
        colCashierUserName.setCellValueFactory(new PropertyValueFactory<>("cashierUsername"));
        colPickUpDate.setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));
        colPickUpTime.setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colReturnTime.setCellValueFactory(new PropertyValueFactory<>("returnTime"));
        colIsDriverWant.setCellValueFactory(new PropertyValueFactory<>("isDriverWant"));

        tblReservations.getColumns().get(8).setCellValueFactory(param -> {
            ImageView btnRemove = OptionButtonsUtil.setRemoveButton();

            btnRemove.setOnMouseClicked(event -> {
                ReservationTM selectedReservation = param.getValue();
                tblReservations.getSelectionModel().select(selectedReservation);
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
        String selectedReservation = tblReservations.getSelectionModel().getSelectedItem().getReservationId();

        if (selectedReservation != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Reservation?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    reservationBO.deleteReservation(selectedReservation);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Reservation Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();

                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No reservation selected to remove...!").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();
        loadCustomerNICs();
        loadCashierUsernames();
        loadLicensePlateNos();

        lblReservationID.setText(reservationBO.getNextReservationId());
        cmbCustomerNIC.setValue("");
        cmbCashierUserName.setValue("");
        txtPickUpDate.setValue(null);
        txtPickUpTime.setText("");
        txtReturnDate.setValue(null);
        txtReturnTime.setText("");
        cmbIsDriverWant.setValue("");
        txtTotalAmount.setText("");
        txtDriverCost.setText("");
        cmbIsDriverWant.setValue("");
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> reservationDTOS = reservationBO.getAllReservations();
        reservationTMS.clear();

        for (ReservationDTO reservationDTO : reservationDTOS) {
            ReservationTM reservationTM = new ReservationTM(
                    reservationDTO.getReservationId(),
                    reservationDTO.getCustomerNic(),
                    reservationDTO.getCashierUsername(),
                    reservationDTO.getPickUpDate(),
                    reservationDTO.getPickUpTime(),
                    reservationDTO.getReturnDate(),
                    reservationDTO.getReturnTime(),
                    reservationDTO.getIsDriverWant()
            );
            reservationTMS.add(reservationTM);
        }
        tblReservations.setItems(reservationTMS);
    }

}
