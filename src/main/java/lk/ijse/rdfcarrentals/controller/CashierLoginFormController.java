package lk.ijse.rdfcarrentals.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rdfcarrentals.bo.custom.BOFactory;
import lk.ijse.rdfcarrentals.bo.custom.CashierBO;
import lk.ijse.rdfcarrentals.dao.custom.impl.CashierDAOImpl;
import lk.ijse.rdfcarrentals.entity.Cashier;

import java.io.IOException;
import java.sql.SQLException;

public class CashierLoginFormController {

    @FXML
    private Button btnCashierLogin;

    @FXML
    private AnchorPane cashierLoginPane;

    @FXML
    private TextField txtFldCashierPassword;

    @FXML
    private TextField txtFldCashierUserName;

    @FXML
    private ImageView cashierLoginBackIcon;

    public static String userName;
    public static String name;

    CashierBO cashierBO = (CashierBO) BOFactory.getInstance().getBO(BOFactory.BOType.CASHIER);
    CashierDAOImpl cashierDAO = new CashierDAOImpl();

    @FXML
    void btnCashierLoginOnAction(ActionEvent event) throws IOException {
        login();
    }

    private void login() {
        try {
            Cashier cashier = cashierBO.login(txtFldCashierUserName.getText(), txtFldCashierPassword.getText());

            if (cashier != null) {
                userName = cashier.getUserName();
                name = cashier.getName();
                txtFldCashierUserName.setStyle(";-fx-border-color: #7367F0;");
                txtFldCashierPassword.setStyle(";-fx-border-color: #7367F0;");
                cashierLoginPane.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CashierDashboardMenuForm.fxml"));
                cashierLoginPane.getChildren().add(load);
            } else {
                Cashier cashierFromDB = cashierDAO.getCashierByUsername(txtFldCashierUserName.getText());
                if (cashierFromDB == null) {
                    txtFldCashierUserName.setStyle(";-fx-border-color: red;");
                    new Alert(Alert.AlertType.ERROR, "Wrong Username. Please Try Again...!").show();
                } else {
                    txtFldCashierPassword.setStyle(";-fx-border-color: red;");
                    new Alert(Alert.AlertType.ERROR, "Wrong Password. Please Try Again...!").show();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cashierLoginBackIconOnAction(MouseEvent event) throws IOException {
        cashierLoginPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomeForm.fxml"));
        cashierLoginPane.getChildren().add(load);
    }

}
