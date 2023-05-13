/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;

import bdd.DatabaseSingleton;
import exception.CustomIOException;
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
import java.util.logging.Logger;

import static constantes.SQLConstants.*;

public class AdminController implements Initializable {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @FXML
    public TableColumn<Object, Object> nameUser;
    @FXML
    public TableColumn<Object, Object> firstNameUser;
    @FXML
    public TableColumn<Object, Object> badgeUser;
    @FXML
    public TableColumn<Object, Object> adresseUser;
    @FXML
    public TableColumn<Object, Object> dateBirthUser;
    @FXML
    public TableColumn<Object, Object> dateHiringUser;
    @FXML
    public TableColumn<Object, Object> numTelUser;
    @FXML
    public TableColumn<Object, Object> idUser;
    @FXML
    public TableColumn<Object, Object> isAdminUser;
    Stage stage;
    Scene scene;
    @FXML
    private Pane pnlStocks;
    @FXML
    private Pane pnlUsers;
    @FXML
    private Pane pnlMoney;
    @FXML
    private Pane pnlProduct;
    @FXML
    private ImageView imgStock;
    @FXML
    private ImageView imgUsers;
    @FXML
    private ImageView imgMoney;
    @FXML
    private ImageView imgProduct;
    @FXML
    public Button btnRefresh;
    @FXML
    public Button btnAdd;
    @FXML
    private TableView<Employee> dataTB;
    private final ObservableList<Employee> data = FXCollections.observableArrayList();
    private int count;

    @FXML
    private TextField inputNameProduct;
    @FXML
    private TextField inputPrice;
    @FXML
    private TextField inputQuantity;
    @FXML
    private TextField inputMinQuantity;
    @FXML
    private TextField inputDop;
    @FXML
    private TextField inputBbd;


    @FXML
    private void handleButtonAction(MouseEvent mouseDragEvent) {
        if (mouseDragEvent.getSource() == imgStock) {
            pnlStocks.toFront();
            logger.info("Test bien appuyé");
        } else if (mouseDragEvent.getSource() == imgMoney) {
            pnlMoney.toFront();
        } else if (mouseDragEvent.getSource() == imgUsers) {
            pnlUsers.toFront();
        } else if (mouseDragEvent.getSource() == imgProduct) {
            pnlProduct.toFront();
        }
    }


    public void mappingLogout(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/login_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur lors du chargement de la page", e);
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
            try {
                throw new CustomIOException("Erreur lors du chargement de la page",e);
            } catch (CustomIOException ex) {
                ex.printStackTrace();
            }
        }

    }


    public void setDataCell() {
        idUser.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameUser.setCellValueFactory(new PropertyValueFactory<>("Name"));
        firstNameUser.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        badgeUser.setCellValueFactory(new PropertyValueFactory<>("Badge"));
        adresseUser.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        dateBirthUser.setCellValueFactory(new PropertyValueFactory<>("DateBirth"));
        dateHiringUser.setCellValueFactory(new PropertyValueFactory<>("DateEmbauche"));
        numTelUser.setCellValueFactory(new PropertyValueFactory<>("NumTel"));
        isAdminUser.setCellValueFactory(new PropertyValueFactory<>("IsAdmin"));
    }


    private void loadData() {
        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement selectEmp2 = db.prepareStatement(SELECTEMPLOYEE);
            ResultSet resultSet = selectEmp2.executeQuery();
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
            selectEmp2.close();
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
    public void exit() {
        Platform.exit();
    }


    @FXML
    private void addProduct() throws IOException, CustomIOException {
        try {
            AddProducts addProducts = new AddProducts(0, inputNameProduct.getText(), inputPrice.getText(), inputQuantity.getText(), inputMinQuantity.getText(), inputDop.getText(), inputBbd.getText());
            if (inputNameProduct.getText().isEmpty() || inputPrice.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("veuillez rentrer des valeurs");
                alert.showAndWait();
            } else {
                DatabaseSingleton db = DatabaseSingleton.getInstance();
                db.connect();
                PreparedStatement insertEmp = db.prepareStatement(INSERTEMPLOYEE);
                insertEmp.setString(1, addProducts.getNameProducts());
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
            throw new CustomIOException("Erreur SQL", e);
        }


    }
}