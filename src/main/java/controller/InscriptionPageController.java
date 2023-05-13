/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;



import bdd.DatabaseSingleton;
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
import models.Users;
import utils.Md5;
import utils.ValidationInput;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import java.util.logging.Logger;

import static constantes.SQLConstants.INSERTUSER;
import static constantes.SQLConstants.SELECTUSERS;


public class InscriptionPageController {

    private static final Logger logger = Logger.getLogger(InscriptionPageController.class.getName());


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
        utilisateur.setPassword(Md5.generateHash(utilisateur.getPassword()));

        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();

            if (isUserExistInDatabase(db, utilisateur)) {
                userNotFound.setText(Constants.USER_EXIST);
                userNotFound.setTextFill(Color.RED);
                resetErrorLabels();
            } else {
                handleUserInputsErrors(utilisateur,event);
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
        mdpError = ValidationInput.PasswordRegister(utilisateur.getPassword());
        mdpNull = ValidationInput.textFieldNull(utilisateur.getPassword());
    }

    private boolean isUserExistInDatabase(DatabaseSingleton db, Users utilisateur) throws SQLException {
        PreparedStatement selectUsers = db.prepareStatement(SELECTUSERS);
        selectUsers.setString(1, utilisateur.getName());
        selectUsers.setString(2, utilisateur.getBadge());
        selectUsers.setString(3, utilisateur.getPassword());
        selectUsers.setBoolean(4, utilisateur.getIsAdmin());
        boolean userExist = selectUsers.executeQuery().next();
        selectUsers.close();
        return userExist;
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
        } else {
            insertNewUserIntoDatabase(utilisateur,event);
        }
    }

    private void setErrorMessages(String nameError, String badgeError, String passwordError) {
        userNotFound.setText("");
        nameErrorLabel.setText(nameError);
        badgeErrorLabel.setText(badgeError);
        pswErrorLabel.setText(passwordError);
    }

    private void insertNewUserIntoDatabase(Users utilisateur,ActionEvent event) throws IOException, SQLException {
        userNotFound.setText(Constants.USER_CREA);
        userNotFound.setTextFill(Color.GREEN);
        pswErrorLabel.setText("");
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        PreparedStatement insertUser = db.prepareStatement(INSERTUSER);
        insertUser.setString(1, utilisateur.getName());
        insertUser.setString(2, utilisateur.getBadge());
        insertUser.setString(3, utilisateur.getPassword());
        insertUser.setBoolean(4, utilisateur.getIsAdmin());
        int n = insertUser.executeUpdate();
        if (n == 1) {
            logger.info("Requête d'insertion de l'utilisateur bien effectuée, NOM : " + utilisateur.getName() +
                    " BADGE : " + utilisateur.getBadge() + " ADMINISTRATEUR : " + utilisateur.getIsAdmin());
        }
        insertUser.close();
        navigateToLoginPage(event);
        db.close();
    }

    private void navigateToLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/login_page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void handleExceptions(Exception e) {
        if (e instanceof IOException) {
            e.printStackTrace();
        } else if (e instanceof SQLIntegrityConstraintViolationException) {
            userNotFound.setText(Constants.USER_EXIST);
        }
    }



    public void mappingLogging(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/login_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur de mapping", e);
        }
    }

    @FXML
    public void exit(ActionEvent event) {
        Platform.exit();
    }


}