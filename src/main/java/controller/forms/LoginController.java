/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller.forms;


import constantes.Constants;
import exception.CustomIOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.forms.LoginServiceImpl;
import services.interfaces.forms.ILoginService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;


public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    private final ILoginService loginService = new LoginServiceImpl();

    @FXML
    public TextField inputBadge;
    @FXML
    public PasswordField inputPsw;
    @FXML
    public Label isConnected;
    @FXML
    public Label errormsg;
    @FXML
    public Button buttonConnexion;
    @FXML
    public Hyperlink buttonRegister;



    Stage stage;
    Scene scene;

    public void login(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String badge = inputBadge.getText();
        String password = inputPsw.getText();

        try {
            if (loginService.authenticateUser(badge, password)) {
                handleSuccessfulLogin(event, loginService.isAdminUser(badge,password));
            } else {

                errormsg.setText(Constants.NOM_PSW_INCORRECT);
            }
        } catch (SQLException e) {

            logger.info(String.valueOf(e));
        }
    }

    private void handleSuccessfulLogin(ActionEvent event, boolean isAdmin) throws IOException {
        isConnected.setText(Constants.CONN_SUCC);
        isConnected.setTextFill(Color.GREEN);
        errormsg.setText("");

        if (isAdmin) {
            // Utilisateur Admin
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/administration/admin.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else {
            // Utilisateur non-administrateur
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/orders/home.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void mappingInscription(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/forms/register.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur lors du chargement de la page", e);
        }

    }
    @FXML
    public void exit() {
        Platform.exit();
    }


}