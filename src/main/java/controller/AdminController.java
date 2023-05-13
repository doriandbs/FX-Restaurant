/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;

import bdd.DatabaseSingleton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.AddProducts;
import models.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static constantes.SQLConstants.*;

public class AdminController implements Initializable {
    @FXML
    public TableColumn<Object, Object> NAME_user;
    @FXML
    public TableColumn<Object, Object> FIRSTNAME_user;
    @FXML
    public TableColumn<Object, Object> BADGE_user;
    @FXML
    public TableColumn<Object, Object> ADRESSE_user;
    @FXML
    public TableColumn<Object, Object> DATEBIRTH_user;
    @FXML
    public TableColumn<Object, Object> DATEHIRING_user;
    @FXML
    public TableColumn<Object, Object> NUMTEL_user;
    @FXML
    public TableColumn<Object, Object> ID_user;
    @FXML
    public TableColumn<Object, Object> ISADMIN_user;
    Stage stage;
    Scene scene;
    @FXML
    private Pane pnl_stocks, pnl_users, pnl_money, pnl_product;
    @FXML
    private ImageView img_stock, img_users, img_money, img_product;
    @FXML
    public Button btn_refresh;
    @FXML
    public Button btn_add;
    @FXML
    private TableView<Employee> dataTB;
    private ObservableList<Employee> data = FXCollections.observableArrayList();
    private int count;

    @FXML
    private TextField input_name_product;
    @FXML
    private TextField input_price;
    @FXML
    private TextField input_quantity;
    @FXML
    private TextField input_min_quantity;
    @FXML
    private TextField input_dop;
    @FXML
    private TextField input_bbd;
    @FXML
    private Button btn_add_product;

    @FXML
    private void handleButtonAction(MouseEvent mouseDragEvent) {
        if (mouseDragEvent.getSource() == img_stock) {
            pnl_stocks.toFront();
            System.out.println("Test bien appuyé");
        } else if (mouseDragEvent.getSource() == img_money) {
            pnl_money.toFront();
        } else if (mouseDragEvent.getSource() == img_users) {
            pnl_users.toFront();
        } else if (mouseDragEvent.getSource() == img_product) {
            pnl_product.toFront();
        }
    }


    public void mappingLogout(ActionEvent event) {
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
    private void refreshTable() {
        try {
            data.clear();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataCell();
            loadData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void setDataCell() {
        ID_user.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NAME_user.setCellValueFactory(new PropertyValueFactory<>("Name"));
        FIRSTNAME_user.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        BADGE_user.setCellValueFactory(new PropertyValueFactory<>("Badge"));
        ADRESSE_user.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        DATEBIRTH_user.setCellValueFactory(new PropertyValueFactory<>("DateBirth"));
        DATEHIRING_user.setCellValueFactory(new PropertyValueFactory<>("DateEmbauche"));
        NUMTEL_user.setCellValueFactory(new PropertyValueFactory<>("NumTel"));
        ISADMIN_user.setCellValueFactory(new PropertyValueFactory<>("IsAdmin"));
    }


    private void loadData() {
        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement SelectEmp2 = db.prepareStatement(SELECTEMPLOYEE);
            ResultSet resultSet = SelectEmp2.executeQuery();
            while (resultSet.next()) {
                data.add(new Employee(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getString("FIRSTNAME"), resultSet.getString("BADGE"),
                        resultSet.getString("ADRESSE"), resultSet.getString("DATEBIRTH"), resultSet.getString("NUMTEL"), resultSet.getString("DATEHIRING"),
                        resultSet.getBoolean("ISADMIN")));
            }
            dataTB.setId("my-table");
            PreparedStatement psc = db.prepareStatement(COUNTEMPLOYEE);
            ResultSet rsc = psc.executeQuery();
            if (rsc.next()) {
                count = rsc.getInt("recordCount");
            }
            dataTB.setPrefHeight((double) count * 29);
            dataTB.setItems(data);
            rsc.close();
            SelectEmp2.close();
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


    public void addEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/addEmployee.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Exit(ActionEvent event) {
        Platform.exit();
    }


    @FXML
    private void add_product(ActionEvent event) throws IOException {
        try {
            AddProducts addProducts = new AddProducts(0, input_name_product.getText(), input_price.getText(), input_quantity.getText(), input_min_quantity.getText(), input_dop.getText(), input_bbd.getText());
            if (input_name_product.getText().isEmpty() || input_price.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("veuillez rentrer des valeurs");
                alert.showAndWait();
            } else {
                DatabaseSingleton db = DatabaseSingleton.getInstance();
                db.connect();
                PreparedStatement insertEmp = db.prepareStatement(INSERTEMPLOYEE);
                insertEmp.setString(1, addProducts.getName_products());
                insertEmp.setString(2, addProducts.getPrice());
                insertEmp.setString(3, addProducts.getQuantity());
                insertEmp.setString(4, addProducts.getMinQuantity());
                insertEmp.setObject(5, addProducts.getDOP());
                insertEmp.setObject(6, addProducts.getBBD());

                insertEmp.executeUpdate();
                insertEmp.close();
                db.close();
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }


    }
}