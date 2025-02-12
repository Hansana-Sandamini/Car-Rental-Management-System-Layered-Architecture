package lk.ijse.rdfcarrentals.controller;

import lk.ijse.rdfcarrentals.bo.custom.BOFactory;
import lk.ijse.rdfcarrentals.bo.custom.CarBO;
import lk.ijse.rdfcarrentals.bo.custom.RepairBO;
import lk.ijse.rdfcarrentals.dto.RepairDTO;
import lk.ijse.rdfcarrentals.entity.Car;
import lk.ijse.rdfcarrentals.view.tdm.RepairTM;
import lk.ijse.rdfcarrentals.dao.OptionButtonsUtil;
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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RepairsFormController implements Initializable {

    @FXML
    private Button btnAddRepair;

    @FXML
    private Button btnRefresh;

    @FXML
    private ComboBox<String> cmbLicensePlateNo;

    @FXML
    private TableColumn<RepairTM, Double> colCost;

    @FXML
    private TableColumn<RepairTM, Date> colDate;

    @FXML
    private TableColumn<RepairTM, String> colDescription;

    @FXML
    private TableColumn<RepairTM, String> colLicensePlateNo;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private TableColumn<RepairTM, String> colRepairID;

    @FXML
    private Label lblRepairID;

    @FXML
    private AnchorPane repairsContent;

    @FXML
    private TableView<RepairTM> tblRepairs;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtFldCost;

    @FXML
    private TextField txtFldDescription;

    @FXML
    private TextField txtFldSearchHere;

    @FXML
    private FontAwesomeIcon searchIcon;

    private final ObservableList<RepairTM> repairTMS = FXCollections.observableArrayList();

    private static boolean isDarkMode = false;

    RepairBO repairBO = (RepairBO) BOFactory.getInstance().getBO(BOFactory.BOType.REPAIR);
    CarBO carBO = (CarBO) BOFactory.getInstance().getBO(BOFactory.BOType.CAR);

    @FXML
    void darkModeIconOnAction(MouseEvent event) {
        if (!isDarkMode) {
            repairsContent.setStyle("-fx-background-color: #293241 ;");
        } else {
            repairsContent.setStyle("-fx-background-color:  #dfe4ea ;");
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void btnAddRepairOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            RepairDTO repairDTO = getTextFieldsValues();
            repairBO.saveRepair(repairDTO);
            new Alert(Alert.AlertType.INFORMATION, "Repair Saved...!").show();
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Fail to Save Repair...!").show();
        }
    }

    RepairDTO getTextFieldsValues() {
        String repairId = lblRepairID.getText();
        String licensePlateNo = cmbLicensePlateNo.getValue();
        String description = txtFldDescription.getText();
        Date date = Date.valueOf(txtDate.getValue());
        double cost = Double.parseDouble(txtFldCost.getText());

        return new RepairDTO(repairId, description, date, cost, licensePlateNo);
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
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
        ArrayList<RepairDTO> repairDTOS = repairBO.getAllRepairs();
        ObservableList<RepairTM> filteredRepairs = FXCollections.observableArrayList();

        for (RepairDTO repairDTO : repairDTOS) {
            if (repairDTO.getRepairId().toLowerCase().contains(searchText) ||
                repairDTO.getLicensePlateNo().toLowerCase().contains(searchText) ||
                repairDTO.getDescription().toLowerCase().contains(searchText)) {
                filteredRepairs.add(new RepairTM(
                        repairDTO.getRepairId(),
                        repairDTO.getLicensePlateNo(),
                        repairDTO.getDescription(),
                        repairDTO.getDate(),
                        repairDTO.getCost()
                ));
            }
        }
        tblRepairs.setItems(filteredRepairs);

        if (searchText.isEmpty()) {
            searchIcon.setVisible(true);
        } else {
            searchIcon.setVisible(false);
        }
    }

    @FXML
    void tblRepairsOnClicked(MouseEvent event) {
        RepairTM selectedItem = tblRepairs.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblRepairID.setText(selectedItem.getRepairId());
            cmbLicensePlateNo.setValue(selectedItem.getLicensePlateNo());
            txtFldDescription.setText(selectedItem.getDescription());
            txtDate.setValue(LocalDate.parse(LocalDate.now().toString()));
            txtFldCost.setText(String.valueOf(selectedItem.getCost()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRepairID.setCellValueFactory(new PropertyValueFactory<>("repairId"));
        colLicensePlateNo.setCellValueFactory(new PropertyValueFactory<>("licensePlateNo"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tblRepairs.getColumns().get(5).setCellValueFactory(param -> {
            ImageView btnRemove = OptionButtonsUtil.setRemoveButton();

            btnRemove.setOnMouseClicked(event -> {
                RepairTM selectedRepair = param.getValue();
                tblRepairs.getSelectionModel().select(selectedRepair);
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
        String selectedRepair = tblRepairs.getSelectionModel().getSelectedItem().getRepairId();

        if (selectedRepair != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Repair?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    repairBO.deleteRepair(selectedRepair);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Repair Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No repair selected to remove...!").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();
        loadLicensePlateNos();

        lblRepairID.setText(repairBO.getNextRepairId());
        cmbLicensePlateNo.setValue("");
        txtFldDescription.setText("");
        txtFldCost.setText("");
        txtDate.setValue(LocalDate.now());
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<RepairDTO> repairDTOS = repairBO.getAllRepairs();
        repairTMS.clear();

        for (RepairDTO repairDTO : repairDTOS) {
            RepairTM repairTM = new RepairTM(
                    repairDTO.getRepairId(),
                    repairDTO.getLicensePlateNo(),
                    repairDTO.getDescription(),
                    repairDTO.getDate(),
                    repairDTO.getCost()
            );
            repairTMS.add(repairTM);
        }
        tblRepairs.setItems(repairTMS);
    }

}
