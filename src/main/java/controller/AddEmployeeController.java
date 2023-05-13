/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;

import bdd.DatabaseSingleton;
import exception.CustomIOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static constantes.SQLConstants.INSERTEMPLOYEE;


public class AddEmployeeController implements Initializable {
    private static final Logger logger = Logger.getLogger(AddEmployeeController.class.getName());


    Stage stage;
    Scene scene;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputFirstname;
    @FXML
    private TextField inputBadge;
    @FXML
    private TextField inputAdresse;
    @FXML
    private TextField inputDatebirth;
    @FXML
    private TextField inputDatehiring;
    @FXML
    private TextField inputNumtel;
    @FXML
    private CheckBox checkIsAdmin;


    @FXML
    private void save(ActionEvent event) throws IOException {
        try {
            Employee employee = new Employee(0, inputName.getText(), inputFirstname.getText(), inputBadge.getText(), inputAdresse.getText(), inputDatebirth.getText(), inputNumtel.getText(), inputDatehiring.getText(), checkIsAdmin.isSelected());
            if (inputName.getText().isEmpty() || inputFirstname.getText().isEmpty()
                || inputBadge.getText().isEmpty() || inputAdresse.getText().isEmpty()||
                    inputDatebirth.getText().isEmpty() || inputNumtel.getText().isEmpty() || inputDatehiring.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("veuillez rentrer des valeurs");
                alert.showAndWait();
            } else {
                DatabaseSingleton db = DatabaseSingleton.getInstance();
                db.connect();
                PreparedStatement insertEmp = db.prepareStatement(INSERTEMPLOYEE);
                insertEmp.setString(1, employee.getName());
                insertEmp.setString(2, employee.getBadge());
                insertEmp.setBoolean(3, employee.getIsAdmin());
                insertEmp.setString(4, employee.getFirstName());
                insertEmp.setString(5, employee.getAdresse());
                insertEmp.setString(6, employee.getDateBirth());
                insertEmp.setString(7, employee.getDateEmbauche());
                insertEmp.setString(8, employee.getNumTel());

                insertEmp.executeUpdate();
                insertEmp.close();
                db.close();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/admin.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } catch (SQLException e) {
            logger.info("Error");
        }


    }

    @FXML
    private void clean(ActionEvent event) {
        inputAdresse.clear();
        inputBadge.clear();
        inputDatebirth.clear();
        inputName.clear();
        inputDatehiring.clear();
        inputNumtel.clear();
        inputFirstname.clear();

    }

    @FXML
    private void mappingBack(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/admin.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur lors du chargement de la page",e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //doNothing
    }
}
