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
import models.administration.Users;
import services.forms.InscriptionServiceImpl;
import services.interfaces.forms.IInscriptionService;
import utils.SHA;
import utils.ValidationInput;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import java.util.logging.Logger;


public class InscriptionPageController {

    private static final Logger logger = Logger.getLogger(InscriptionPageController.class.getName());

    private final IInscriptionService inscriptionService = new InscriptionServiceImpl();

    @FXML
    public TextField inputNameRegister;
    @FXML
    public TextField inputBadgeRegister;
    @FXML
    public PasswordField inputPswRegister;

    @FXML
    public Label nameErrorLabel;
    @FXML
    public Label badgeErrorLabel;
    @FXML
    public Label pswErrorLabel;
    @FXML
    public Label userNotFound;
    @FXML
    public Button buttonInscription;


    @FXML
    public CheckBox isAdmin;
    boolean nameError;
    boolean badgeError;
    boolean mdpError;
    boolean mdpNull;
    Stage stage;
    Scene scene;

    public void addUser(ActionEvent event) throws NoSuchAlgorithmException, CustomIOException {
        Users utilisateur = createNewUser();
        validateUserInputs(utilisateur);
        utilisateur.setPassword(SHA.generateHash(utilisateur.getPassword()));
        try {
            if (inscriptionService.isUserExistInDatabase(utilisateur)) {
                userNotFound.setText(Constants.USER_EXIST);
                userNotFound.setTextFill(Color.RED);
                resetErrorLabels();
            } else {
                handleUserInputsErrors(utilisateur, event);
            }
        } catch (IOException | SQLIntegrityConstraintViolationException e) {
            handleExceptions(e);
        } catch (SQLException e) {
            throw new CustomIOException("Problème relié à la BDD", e);
        }
    }

    private Users createNewUser() {
        Users utilisateur = new Users();
        utilisateur.setName(inputNameRegister.getText());
        utilisateur.setBadge(inputBadgeRegister.getText());
        utilisateur.setPassword(inputPswRegister.getText());
        utilisateur.setIsAdmin(isAdmin.isSelected());
        return utilisateur;
    }

    private void validateUserInputs(Users utilisateur) {
        nameError = ValidationInput.textFieldNull(utilisateur.getName());
        badgeError = ValidationInput.textFieldNull(String.valueOf(utilisateur.getBadge()));
        mdpError = ValidationInput.passwordRegister(utilisateur.getPassword());
        mdpNull = ValidationInput.textFieldNull(utilisateur.getPassword());
    }

    private void resetErrorLabels() {
        nameErrorLabel.setText("");
        badgeErrorLabel.setText("");
        pswErrorLabel.setText("");
    }

    private void handleUserInputsErrors(Users utilisateur, ActionEvent event) throws IOException, SQLException {
        if (nameError && badgeError && mdpNull) {
            setErrorMessages(Constants.NOM_REC, Constants.BADGE_REC, Constants.PSW_REC);
        } else if (nameError && badgeError) {
            setErrorMessages(Constants.NOM_REC, Constants.BADGE_REC, "");
        } else if (badgeError && mdpNull) {
            setErrorMessages("", Constants.BADGE_REC, Constants.PSW_REC);
        } else if (nameError && mdpNull) {
            setErrorMessages(Constants.NOM_REC, "", Constants.PSW_REC);
        } else if (nameError) {
            setErrorMessages(Constants.NOM_REC, "", "");
        } else if (badgeError) {
            setErrorMessages("", Constants.BADGE_REC, "");
        } else if (mdpNull) {
            setErrorMessages("", "", Constants.PSW_REC);
        } else if (mdpError) {
            setErrorMessages("", "", Constants.PSW_REGISTER);
        }  else {
            inscriptionService.insertNewUserIntoDatabase(utilisateur);
            navigateToLoginPage(event);
        }
    }

    private void setErrorMessages(String nameError, String badgeError, String passwordError) {
        userNotFound.setText("");
        nameErrorLabel.setText(nameError);
        badgeErrorLabel.setText(badgeError);
        pswErrorLabel.setText(passwordError);
    }


    private void navigateToLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/forms/login_page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void handleExceptions(Exception e) {
        if (e instanceof IOException) {
            logger.info("Erreur");
        } else if (e instanceof SQLIntegrityConstraintViolationException) {
            userNotFound.setText(Constants.USER_EXIST);
        }
    }



    public void mappingLogging(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/forms/login_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur de mapping", e);
        }
    }

    @FXML
    public void exit() {
        Platform.exit();
    }


}