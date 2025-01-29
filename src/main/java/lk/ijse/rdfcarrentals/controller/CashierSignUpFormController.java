package lk.ijse.rdfcarrentals.controller;

import lk.ijse.rdfcarrentals.bo.custom.BOFactory;
import lk.ijse.rdfcarrentals.bo.custom.CashierBO;
import lk.ijse.rdfcarrentals.dto.CashierDTO;
import lk.ijse.rdfcarrentals.view.tdm.CashierTM;
import lk.ijse.rdfcarrentals.dao.OptionButtonsUtil;
import lk.ijse.rdfcarrentals.dao.ValidationUtil;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CashierSignUpFormController implements Initializable {

    @FXML
    private Label lblHeadingUserName;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane cashierSignUpPane;

    @FXML
    private TableColumn<CashierTM, String> colContactNumber;

    @FXML
    private TableColumn<CashierTM, String> colEmail;

    @FXML
    private TableColumn<CashierTM, String> colName;

    @FXML
    private TableColumn<CashierTM, String> colPassword;

    @FXML
    private TableColumn<CashierTM, String> colUserName;

    @FXML
    private TableView<CashierTM> tblCashiers;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private TextField txtFldContactNumber;

    @FXML
    private TextField txtFldEmail;

    @FXML
    private TextField txtFldName;

    @FXML
    private TextField txtFldPassword;

    @FXML
    private TextField txtFldSearchHere;

    @FXML
    private TextField txtFldUserName;

    @FXML
    private FontAwesomeIcon searchIcon;

    private static boolean isDarkMode = false;

    CashierBO cashierBO = (CashierBO) BOFactory.getInstance().getBO(BOFactory.BOType.CASHIER);

    @FXML
    void darkModeIconOnAction(MouseEvent event) {
        if (!isDarkMode) {
            cashierSignUpPane.setStyle("-fx-background-color: #293241 ;");
        } else {
            cashierSignUpPane.setStyle("-fx-background-color:  #dfe4ea ;");
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (validateTextFields()) {
            try {
                CashierDTO cashierDTO = getTextFieldsValues();

                cashierBO.saveCashier(cashierDTO);
                new Alert(Alert.AlertType.INFORMATION, "Cashier Saved...!").show();
                refreshPage();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Cashier: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void txtFldSearchHereOnAction(KeyEvent event) throws SQLException, ClassNotFoundException {
        String searchText = txtFldSearchHere.getText().toLowerCase();
        ArrayList<CashierDTO> cashierDTOS = cashierBO.getAllCashiers();
        ObservableList<CashierTM> filteredCashiers = FXCollections.observableArrayList();

        for (CashierDTO cashierDTO : cashierDTOS) {
            if (cashierDTO.getUserName().toLowerCase().contains(searchText) ||
                    cashierDTO.getName().toLowerCase().contains(searchText)) {
                    filteredCashiers.add(new CashierTM(
                            cashierDTO.getUserName(),
                            cashierDTO.getPassword(),
                            cashierDTO.getName(),
                            cashierDTO.getEmail(),
                            cashierDTO.getContactNumber()
                    ));
            }
        }
        tblCashiers.setItems(filteredCashiers);

        if (searchText.isEmpty()) {
            searchIcon.setVisible(true);
        } else {
            searchIcon.setVisible(false);
        }
    }

    CashierDTO getTextFieldsValues() {
        String userName = txtFldUserName.getText();
        String password = txtFldPassword.getText();
        String name = txtFldName.getText();
        String email = txtFldEmail.getText();
        String contactNumber = txtFldContactNumber.getText();

        return new CashierDTO(userName, password, name, contactNumber, email);
    }

    boolean validateTextFields(){
        boolean isValidName = ValidationUtil.isValidName(txtFldName);
        boolean isValidEmail = ValidationUtil.isValidEmail(txtFldEmail);
        boolean isValidContactNumber = ValidationUtil.isValidContactNumber(txtFldContactNumber);

        return isValidName && isValidEmail && isValidContactNumber;
    }

    @FXML
    void tblCashiersOnClicked(MouseEvent event) {
        CashierTM selectedItem = tblCashiers.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtFldUserName.setText(selectedItem.getUserName());
            txtFldPassword.setText(selectedItem.getPassword());
            txtFldName.setText(selectedItem.getName());
            txtFldContactNumber.setText(selectedItem.getContactNumber());
            txtFldEmail.setText(selectedItem.getEmail());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblCashiers.getColumns().get(5).setCellValueFactory(param -> {
            ImageView btnRemove = OptionButtonsUtil.setRemoveButton();
            ImageView btnUpdate = OptionButtonsUtil.setUpdateButton();

            btnRemove.setOnMouseClicked(event -> {
                CashierTM selectedCashier = param.getValue();
                tblCashiers.getSelectionModel().select(selectedCashier);
                setBtnRemove(event);
            });

            btnUpdate.setOnMouseClicked(event -> {
                CashierTM selectedCashier = param.getValue();
                tblCashiers.getSelectionModel().select(selectedCashier);
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

    private void setBtnRemove(MouseEvent event) {
        String selectedCashier = tblCashiers.getSelectionModel().getSelectedItem().getUserName();

        if (selectedCashier != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Cashier?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    cashierBO.deleteCashier(selectedCashier);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Cashier Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Cashier selected to Remove...!").show();
        }
    }

    private void setBtnUpdate(MouseEvent event) throws SQLException, ClassNotFoundException {
        String selectedCashier = tblCashiers.getSelectionModel().getSelectedItem().getUserName();

        if (selectedCashier != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this Cashier?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {

                if (validateTextFields()) {
                    try {
                        CashierDTO cashierDTO = getTextFieldsValues();

                        cashierBO.updateCashier(cashierDTO);
                        new Alert(Alert.AlertType.INFORMATION, "Cashier Updated...!").show();
                        refreshPage();

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to Update Cashier: " + e.getMessage()).show();
                    }
                }
            } else {
                refreshPage();
            }
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();

        txtFldUserName.setText("");
        txtFldPassword.setText("");
        txtFldName.setText("");
        txtFldEmail.setText("");
        txtFldContactNumber.setText("");
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<CashierDTO> cashierDTOS = cashierBO.getAllCashiers();
        ObservableList<CashierTM> cashierTMS = FXCollections.observableArrayList();

        for (CashierDTO cashierDTO : cashierDTOS) {
            CashierTM cashierTM = new CashierTM(
                    cashierDTO.getUserName(),
                    cashierDTO.getPassword(),
                    cashierDTO.getName(),
                    cashierDTO.getContactNumber(),
                    cashierDTO.getEmail()
            );
            cashierTMS.add(cashierTM);
        }
        tblCashiers.setItems(cashierTMS);
    }

}