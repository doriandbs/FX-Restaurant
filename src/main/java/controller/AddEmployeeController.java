package controller;

import bdd.DatabaseSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

import static constantes.SQLConstants.INSERTEMPLOYEE;


public class AddEmployeeController implements Initializable {

    Stage stage;
    Scene scene;
    @FXML
    private TextField input_name;
    @FXML
    private TextField input_firstname;
    @FXML
    private TextField input_badge;
    @FXML
    private TextField input_adresse;
    @FXML
    private TextField input_datebirth;
    @FXML
    private TextField input_datehiring;
    @FXML
    private TextField input_numtel;
    @FXML
    private CheckBox check_isadmin;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_clean;

    @FXML
    private void save() throws SQLException {
        try {
            Employee employee = new Employee(0, input_name.getText(), input_firstname.getText(), input_badge.getText(), input_adresse.getText(), input_datebirth.getText(), input_numtel.getText(), input_datehiring.getText(), check_isadmin.isSelected());
            if (input_name.getText().isEmpty() || input_firstname.getText().isEmpty()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }


    }

    @FXML
    private void clean() {

    }

    @FXML
    private void MappingBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/admin.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
