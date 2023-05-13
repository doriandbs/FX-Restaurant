/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;

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
import services.EmployeeServiceImpl;
import services.interfaces.IEmployeeService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class AddEmployeeController implements Initializable {


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

    private final IEmployeeService employeeService;

    public AddEmployeeController() {
        this.employeeService = new EmployeeServiceImpl();
    }
    @FXML
    protected void save() {
        Employee employee = new Employee(0, inputName.getText(), inputFirstname.getText(), inputBadge.getText(), inputAdresse.getText(), inputDatebirth.getText(), inputNumtel.getText(), inputDatehiring.getText(), checkIsAdmin.isSelected());
        if (inputName.getText().isEmpty() || inputFirstname.getText().isEmpty()
                || inputBadge.getText().isEmpty() || inputAdresse.getText().isEmpty()||
                inputDatebirth.getText().isEmpty() || inputNumtel.getText().isEmpty() || inputDatehiring.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("veuillez rentrer des valeurs");
            alert.showAndWait();
        } else {
            employeeService.saveEmployee(employee);
        }
    }

    @FXML
    protected void clean() {
        inputAdresse.clear();
        inputBadge.clear();
        inputDatebirth.clear();
        inputName.clear();
        inputDatehiring.clear();
        inputNumtel.clear();
        inputFirstname.clear();

    }

    @FXML
    protected void mappingBack(ActionEvent event) throws CustomIOException {
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
