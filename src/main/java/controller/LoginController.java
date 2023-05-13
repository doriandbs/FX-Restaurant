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
import utils.ValidationInput;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.logging.Logger;

import static constantes.SQLConstants.SELECTUSER;
import static utils.Md5.generateHash;


public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());


    private static final String BADGE="BADGE";
    private static final String ISADMIN="ISADMIN";
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
    @FXML
    boolean verifBadge;
    @FXML
    boolean verifPassword;


    Stage stage;
    Scene scene;

    public void login(ActionEvent event) throws NoSuchAlgorithmException {

        Users utilisateur = new Users();
        utilisateur.setBadge(inputBadge.getText());
        String pswHash = generateHash(inputPsw.getText());
        utilisateur.setPassword(pswHash);

        verifBadge = ValidationInput.textFieldNull(utilisateur.getBadge());
        verifPassword = ValidationInput.textFieldNull(utilisateur.getPassword());

        if (verifBadge && verifPassword) errormsg.setText(Constants.VERIF_CH);
        else if (verifBadge) errormsg.setText(Constants.BADGE_REC);
        else if (verifPassword) errormsg.setText(Constants.PSW_REC);
        else errormsg.setText(Constants.USER_NOT_FOUND);
        if (!verifBadge && !verifPassword) {

            try {
                DatabaseSingleton db = DatabaseSingleton.getInstance();
                db.connect();
                PreparedStatement selectUser = db.prepareStatement(SELECTUSER);

                selectUser.setString(1, utilisateur.getBadge());
                selectUser.setString(2, utilisateur.getPassword());
                ResultSet resultSet = selectUser.executeQuery();

                while (resultSet.next()) {
                    if (Objects.equals(resultSet.getString(BADGE), utilisateur.getBadge())
                            && Objects.equals(resultSet.getString("PASSWORD"), utilisateur.getPassword())) {
                        //rajouter condition if si ISADMIN 1 ou 0
                        if (resultSet.getInt(ISADMIN) == 1) { // Utilisateur Admin
                            logger.info(resultSet.getString("NOM") + "badge : " + resultSet.getString(BADGE) + " est connecté / IS ADMIN : " + resultSet.getInt(ISADMIN));
                            isConnected.setText(Constants.CONN_SUCC);
                            isConnected.setTextFill(Color.GREEN);
                            errormsg.setText("");
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/admin.fxml")));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();


                            //DIRECTION SUR CETTE PAGE


                        } else { //si utilisateur pas admin
                            logger.info(resultSet.getString("NOM") + "badge : " + resultSet.getString(BADGE) + " est connecté / IS ADMIN : " + resultSet.getInt(ISADMIN));
                            isConnected.setText(Constants.CONN_SUCC);
                            isConnected.setTextFill(Color.GREEN);
                            errormsg.setText("");
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/home.fxml")));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();


                            //DIRECTION SUR CETTE PAGE
                        }

                    } else {
                        errormsg.setText(Constants.NOM_PSW_INCORRECT);
                    }
                }
                selectUser.close();
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void mappingInscription(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/register.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur lors du chargement de la page", e);
        }

    }
    @FXML
    public void exit(ActionEvent event) {
        Platform.exit();
    }


}