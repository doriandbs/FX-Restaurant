package controller;


import bdd.DatabaseSingleton;
import constantes.Constants;
import javafx.event.ActionEvent;
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

import static constantes.SQLConstants.SELECTUSER;
import static utils.Md5.generateHash;


public class LoginController {
    public TextField input_badge;
    public PasswordField input_psw;
    public Label isConnected;
    public Label errormsg;
    public Button button_connexion;
    public Hyperlink button_register;
    boolean VerifBadge;
    boolean VerifPassword;


    Stage stage;
    Scene scene;

    public void login(ActionEvent event) throws NoSuchAlgorithmException {

        Users utilisateur = new Users();
        utilisateur.setBadge(input_badge.getText());
        String pswHash = generateHash(input_psw.getText());
        utilisateur.setPassword(pswHash);

        VerifBadge = ValidationInput.textFieldNull(utilisateur.getBadge());
        VerifPassword = ValidationInput.textFieldNull(utilisateur.getPassword());

        if (VerifBadge && VerifPassword) errormsg.setText(Constants.verifCh);
        else if (VerifBadge) errormsg.setText(Constants.badgeRec);
        else if (VerifPassword) errormsg.setText(Constants.pswRec);
        else errormsg.setText(Constants.userNotFound);
        if (!VerifBadge && !VerifPassword) {

            try {
                DatabaseSingleton db = DatabaseSingleton.getInstance();
                db.connect();
                PreparedStatement SelectUser = db.prepareStatement(SELECTUSER);

                SelectUser.setString(1, utilisateur.getBadge());
                SelectUser.setString(2, utilisateur.getPassword());
                ResultSet resultSet = SelectUser.executeQuery();

                while (resultSet.next()) {
                    if (Objects.equals(resultSet.getString("BADGE"), utilisateur.getBadge())
                            && Objects.equals(resultSet.getString("PASSWORD"), utilisateur.getPassword())) {
                        //rajouter condition if si ISADMIN 1 ou 0
                        if (resultSet.getInt("ISADMIN") == 1) { // Utilisateur Admin
                            System.out.println(resultSet.getString("NOM") + "badge : " + resultSet.getString("BADGE") + " est connecté / IS ADMIN : " + resultSet.getInt("ISADMIN"));
                            isConnected.setText(Constants.connSucc);
                            isConnected.setTextFill(Color.GREEN);
                            errormsg.setText("");
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/admin.fxml")));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();


                            //DIRECTION SUR CETTE PAGE


                        } else { //si utilisateur pas admin
                            System.out.println(resultSet.getString("NOM") + "badge : " + resultSet.getString("BADGE") + " est connecté / IS ADMIN : " + resultSet.getInt("ISADMIN"));
                            isConnected.setText(Constants.connSucc);
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
                        errormsg.setText(Constants.nomOrpswI);
                    }
                }
                SelectUser.close();
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void MappingInscription(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/register.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}