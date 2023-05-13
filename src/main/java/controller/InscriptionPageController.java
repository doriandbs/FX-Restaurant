/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;



import bdd.DatabaseSingleton;
import constantes.Constants;
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

import static constantes.SQLConstants.INSERTUSER;
import static constantes.SQLConstants.SELECTUSERS;


public class InscriptionPageController {

    public TextField input_nameRegister;
    public TextField input_badgeRegister;
    public PasswordField input_pswRegister;

    public Label name_errorLabel;
    public Label badge_ErrorLabel;
    public Label psw_errorLabel;
    public Label userNotFound;
    public Button button_inscription;

    public CheckBox isAdmin;
    boolean nameError;
    boolean badgeError;
    boolean mdpError;
    boolean mdpNull;
    Stage stage;
    Scene scene;

    public void addUser(ActionEvent event) throws NoSuchAlgorithmException {
        Users utilisateur = new Users();
        utilisateur.setName(input_nameRegister.getText());
        utilisateur.setBadge(input_badgeRegister.getText());
        utilisateur.setPassword(input_pswRegister.getText());
        utilisateur.setIsAdmin(isAdmin.isSelected());

        nameError = ValidationInput.textFieldNull(utilisateur.getName());
        badgeError = ValidationInput.textFieldNull(String.valueOf(utilisateur.getBadge()));
        mdpError = ValidationInput.PasswordRegister(utilisateur.getPassword());
        mdpNull = ValidationInput.textFieldNull(utilisateur.getPassword());

        utilisateur.setPassword(Md5.generateHash(utilisateur.getPassword()));

        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement SelectUsers = db.prepareStatement(SELECTUSERS);
            SelectUsers.setString(1, utilisateur.getName());
            SelectUsers.setString(2, utilisateur.getBadge());
            SelectUsers.setString(3, utilisateur.getPassword());
            SelectUsers.setBoolean(4, utilisateur.getIsAdmin());


            if (SelectUsers.executeQuery().next()) {
                userNotFound.setText(Constants.userExist);
                userNotFound.setTextFill(Color.RED);
                name_errorLabel.setText("");
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText("");
            } else if (nameError && badgeError && mdpNull) {
                name_errorLabel.setText(Constants.nomRec);
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText(Constants.pswRec);
            } else if (nameError && badgeError) {
                name_errorLabel.setText(Constants.nomRec);
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText("");
            } else if (badgeError && mdpNull) {
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText(Constants.pswRec);
                name_errorLabel.setText("");
            } else if (nameError && mdpNull) {
                name_errorLabel.setText(Constants.nomRec);
                psw_errorLabel.setText(Constants.pswRec);
                badge_ErrorLabel.setText("");
            } else if (nameError) {
                userNotFound.setText("");
                name_errorLabel.setText(Constants.nomRec);
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText("");
            } else if (badgeError) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText("");
            } else if (mdpNull) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText(Constants.pswRec);
            } else if (mdpError) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText(Constants.pswRegister);
            }
            else {
                userNotFound.setText(Constants.userCreat);
                userNotFound.setTextFill(Color.GREEN);
                psw_errorLabel.setText("");
                PreparedStatement InsertUser = db.prepareStatement(INSERTUSER);
                InsertUser.setString(1, utilisateur.getName());
                InsertUser.setString(2, utilisateur.getBadge());
                InsertUser.setString(3, utilisateur.getPassword());
                InsertUser.setBoolean(4, utilisateur.getIsAdmin());
                int n = InsertUser.executeUpdate();
                if (n == 1) {
                    System.out.println("Requête d'insertion de l'utilisateur bien effectuée, NOM : " + utilisateur.getName() +
                            " BADGE : " + utilisateur.getBadge() + " ADMINISTRATEUR : " + utilisateur.getIsAdmin());
                }
                InsertUser.close();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/login_page.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            SelectUsers.close();
            db.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLIntegrityConstraintViolationException e) {
            userNotFound.setText(Constants.userExist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void MappingLogging(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/login_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void Exit(ActionEvent event) {
        Platform.exit();
    }


}