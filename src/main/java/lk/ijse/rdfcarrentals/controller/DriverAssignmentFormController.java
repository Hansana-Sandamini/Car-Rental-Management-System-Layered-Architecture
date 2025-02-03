package lk.ijse.rdfcarrentals.controller;

import lk.ijse.rdfcarrentals.bo.custom.BOFactory;
import lk.ijse.rdfcarrentals.bo.custom.DriverAssignmentBO;
import lk.ijse.rdfcarrentals.dto.DriverAssignmentDTO;
import lk.ijse.rdfcarrentals.view.tdm.DriverAssignmentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DriverAssignmentFormController implements Initializable {

    @FXML
    private TableColumn<DriverAssignmentTM, String> colDriverNic;

    @FXML
    private TableColumn<DriverAssignmentTM, String> colLicensePlateNo;

    @FXML
    private TableColumn<DriverAssignmentTM, Double> colPricePerKm;

    @FXML
    private TableColumn<DriverAssignmentTM, String> colTime;

    @FXML
    private TableColumn<DriverAssignmentTM, Date> colDate;

    @FXML
    private AnchorPane driverAssignmentPane;

    @FXML
    private TableView<DriverAssignmentTM> tblDriverAssignment;

    private final ObservableList<DriverAssignmentTM> driverAssignmentTMS = FXCollections.observableArrayList();

    DriverAssignmentBO driverAssignmentBO = (DriverAssignmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER_ASSIGNMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colLicensePlateNo.setCellValueFactory(new PropertyValueFactory<>("licensePlateNo"));
        colDriverNic.setCellValueFactory(new PropertyValueFactory<>("driverNic"));
        colPricePerKm.setCellValueFactory(new PropertyValueFactory<>("pricePerKm"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        loadDriverAssignments();
    }

    private void loadDriverAssignments() {
        try {
            ArrayList<DriverAssignmentDTO> driverAssignmentDTOS = driverAssignmentBO.getAllDriverAssignments();
            driverAssignmentTMS.clear();

            for (DriverAssignmentDTO driverAssignmentDTO : driverAssignmentDTOS) {
                driverAssignmentTMS.add(new DriverAssignmentTM(
                        driverAssignmentDTO.getLicensePlateNo(),
                        driverAssignmentDTO.getDriverNic(),
                        driverAssignmentDTO.getPricePerKm(),
                        driverAssignmentDTO.getDate(),
                        driverAssignmentDTO.getTime()
                ));
            }
            tblDriverAssignment.setItems(driverAssignmentTMS);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Driver Assignments: " + e.getMessage()).show();
        }
    }
}