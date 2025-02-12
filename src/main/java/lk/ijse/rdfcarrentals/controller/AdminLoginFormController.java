package lk.ijse.rdfcarrentals.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rdfcarrentals.bo.custom.AdminBO;
import lk.ijse.rdfcarrentals.bo.custom.BOFactory;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.AdminDAOImpl;
import lk.ijse.rdfcarrentals.entity.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminLoginFormController implements Initializable {

    @FXML
    private AnchorPane adminLoginPane;

    @FXML
    private Button btnAdminLogin;

    @FXML
    private TextField txtFldAdminPassword;

    @FXML
    private TextField txtFldAdminUserName;

    @FXML
    private ImageView adminLoginBackIcon;

    public static String userName;
    public static String name1;

    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOType.ADMIN);
    AdminDAOImpl adminDAO = (AdminDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);

    @FXML
    void btnAdminLoginOnAction(ActionEvent event) throws IOException {
        login();
    }

    private void login() {
        try {
            Admin admin = adminBO.login(txtFldAdminUserName.getText(), txtFldAdminPassword.getText());

            if (admin != null) {
                userName = admin.getUserName();
                name1 = admin.getName();
                txtFldAdminUserName.setStyle(";-fx-border-color: #7367F0;");
                txtFldAdminPassword.setStyle(";-fx-border-color: #7367F0;");
                adminLoginPane.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/AdminDashboardMenuForm.fxml"));
                adminLoginPane.getChildren().add(load);
            } else {
                Admin adminFromDB = adminDAO.getAdminByUsername(txtFldAdminUserName.getText());
                if (adminFromDB == null) {
                    txtFldAdminUserName.setStyle(";-fx-border-color: red;");
                    new Alert(Alert.AlertType.ERROR, "Wrong Username. Please Try Again...!").show();
                } else {
                    txtFldAdminPassword.setStyle(";-fx-border-color: red;");
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
    void adminLoginBackIconOnAction(MouseEvent event) throws IOException {
        adminLoginPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomeForm.fxml"));
        adminLoginPane.getChildren().add(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> txtFldAdminUserName.requestFocus());

        txtFldAdminUserName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtFldAdminPassword.requestFocus();
            }
        });

        txtFldAdminPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });
    }
}
