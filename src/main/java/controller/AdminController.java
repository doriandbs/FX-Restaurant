/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;

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
import services.AdminServiceImpl;
import services.interfaces.IAdminService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminController implements Initializable {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    private final IAdminService adminService;

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


    public AdminController() {
        this.adminService = new AdminServiceImpl();
    }
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
            logger.info(String.valueOf(e));
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
                logger.info(String.valueOf(e));
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
        ObservableList<Employee> employees = FXCollections.observableArrayList(adminService.getAllEmployees());

        dataTB.setItems(employees);
        dataTB.setId("my-table");

        double count = employees.size();
        dataTB.setPrefHeight(count * 29);
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
    private void addProduct() throws CustomIOException {
            AddProducts addProducts = new AddProducts(0, inputNameProduct.getText(), inputPrice.getText(), inputQuantity.getText(), inputMinQuantity.getText(), inputDop.getText(), inputBbd.getText());
            if (inputNameProduct.getText().isEmpty() || inputPrice.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("veuillez rentrer des valeurs");
                alert.showAndWait();
            } else {
                adminService.addProduct(addProducts);
            }


    }


}