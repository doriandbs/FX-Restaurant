/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller.administration;

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
import models.administration.ChiffreAffaire;
import models.administration.Employee;
import services.administration.AdminServiceImpl;
import services.interfaces.administration.IAdminService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    /** ChiffreAffaire */
    @FXML
    public TableColumn<Object, Object> month;
    @FXML
    public TableColumn<Object, Object> montant;
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
    @FXML
    private TableView<ChiffreAffaire> dataCA;
    private final ObservableList<Employee> data = FXCollections.observableArrayList();

    @FXML
    private TextField inputQuantity;
    @FXML
    private TextField inputQuantity1;
    @FXML
    private TextField inputQuantity2;

    @FXML
    private TextField inputQuantityBurger;
    @FXML
    private TextField inputQuantityBurger1;
    @FXML
    private TextField inputQuantityBurger2;
    @FXML
    private ComboBox<String> inputNameProduct;
    @FXML
    private ComboBox<String> inputNameProduct1;
    @FXML
    private ComboBox<String> inputNameProduct2;

    @FXML
    private ComboBox<String> inputNameProductBurger;
    @FXML
    private ComboBox<String> inputNameProductBurger1;
    @FXML
    private ComboBox<String> inputNameProductBurger2;

    private int count;


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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/forms/login_page.fxml")));
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
            loadDataEmpl();
        } catch (Exception e) {
            logger.info(String.valueOf(e));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataCell();
            loadDataEmpl();
            loadDataCa();
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

        month.setCellValueFactory(new PropertyValueFactory("Month"));
        montant.setCellValueFactory(new PropertyValueFactory("TotalMontant"));
    }

    private void loadDataEmpl() throws SQLException {
        ObservableList<Employee> employees = FXCollections.observableArrayList(adminService.getAllEmployees());

        dataTB.setItems(employees);
        dataTB.setId("my-table");
        count=adminService.countEmpl();
        dataTB.setPrefHeight(count * 18);
        dataTB.setItems(data);
    }

    private void loadDataCa() throws SQLException {
        ObservableList<ChiffreAffaire> ca = FXCollections.observableArrayList(adminService.getAllChiffreAffaire());
        dataCA.setItems(ca);
        dataCA.setId("table-ca");
        count=adminService.countEmpl();
        dataCA.setPrefHeight(count * 18);
        for(ChiffreAffaire affaire : ca){
            System.out.println(affaire.getTotalMontant());
        }
    }


    @FXML
    private void loadDataListProduct(MouseEvent event) throws CustomIOException {

        try {
            if(inputNameProduct.getItems().isEmpty()|| inputNameProduct1.getItems().isEmpty() || inputNameProduct2.getItems().isEmpty()
            ||inputNameProductBurger.getItems().isEmpty()||inputNameProductBurger1.getItems().isEmpty() ||inputNameProductBurger2.getItems().isEmpty()){
                if (event.getSource() == inputNameProduct
                        || event.getSource() == inputNameProduct1
                        || event.getSource() == inputNameProduct2) {

                    ObservableList<String> items = adminService.loadDataListProduct();
                    inputNameProduct.getItems().addAll(items);
                    inputNameProduct1.getItems().addAll(items);
                    inputNameProduct2.getItems().addAll(items);

                } else {
                    ObservableList<String> items = adminService.loadDataListProductBurg();
                    inputNameProductBurger.getItems().addAll(items);
                    inputNameProductBurger1.getItems().addAll(items);
                    inputNameProductBurger2.getItems().addAll(items);

                }}else{
                logger.info("Erreur d'instanciation de données");
            }
        } catch(SQLException | IOException e){
        throw new CustomIOException(e.getMessage(),e);
        }

    }




    public void addEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/administration/addEmployee.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.centerOnScreen();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    /*private boolean checkInput() {
        String inputNameProd = inputNameProduct.getValue();
        String inputNameProdBurger = inputNameProductBurger.getValue();
        boolean inputQuantBurger = inputQuantityBurger.getText().isEmpty();
        boolean inputQuantProd = inputQuantity.getText().isEmpty();

        if ((!inputNameProd.isEmpty() && !inputQuantProd) || (!inputNameProdBurger.isEmpty() && !inputQuantBurger)) {
            return true;
        } else if ((!inputQuantProd && !inputNameProd.isEmpty() && inputNameProdBurger.isEmpty()) ||
                (!inputQuantBurger && !inputNameProdBurger.isEmpty() && inputNameProd.isEmpty())) {
            return false;
        } else {
            // Handle other cases if needed
            return false; // Default return value if none of the conditions are met
        }
    }*/


    @FXML
    private void addProduct() throws CustomIOException {
        String inputNameProductGet= inputNameProduct.getValue();
        String inputNameProduct1Get = inputNameProduct1.getValue();
        String inputNameProduct2Get = inputNameProduct2.getValue();

        String quantityNameProd = inputQuantity.getText();
        String quantityNameProd1 = inputQuantity1.getText();
        String quantityNameProd2 = inputQuantity2.getText();
        if(inputQuantity.getText().isEmpty()) inputQuantity.setText("0");
        if(inputQuantity1.getText().isEmpty()) inputQuantity1.setText("0");
        if(inputQuantity2.getText().isEmpty()) inputQuantity2.setText("0");
        try{
            adminService.addProduct(inputNameProductGet,inputNameProduct1Get,inputNameProduct2Get,
                    quantityNameProd,quantityNameProd1,quantityNameProd2);
            clearInputs();


        } catch (SQLException | IOException e) {
            throw new CustomIOException(e.getMessage(), e);
        }

        //       if(isCheck){
//           //Bon trucs
//           System.out.println("Cool ! ");
//       }else{
//           System.out.println("Error");
//       }
//
//
//            *//*AddProducts addProducts = new AddProducts(0, inputNameProduct.getText(), inputPrice.getText(), inputQuantity.getText(), inputMinQuantity.getText(), inputDop.getText(), inputBbd.getText());
//            if (inputNameProduct.getText().isEmpty() || inputPrice.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("veuillez rentrer des valeurs");
//                alert.showAndWait();
//            } else {
//                adminService.addProduct(addProducts);
//            }


    }
    @FXML
    private void addProductBurg() throws CustomIOException {

        String inputNameProductBurgGet= inputNameProductBurger.getValue();
        String inputNameProductBurgGet1 = inputNameProductBurger1.getValue();
        String inputNameProductBurgGet2 = inputNameProductBurger2.getValue();

        String quantityNameProdBurg = inputQuantityBurger.getText();
        String quantityNameProdBurg1 = inputQuantityBurger1.getText();
        String quantityNameProdBurg2 = inputQuantityBurger2.getText();
        if(inputQuantityBurger.getText().isEmpty()) inputQuantityBurger.setText("0");
        if(inputQuantityBurger1.getText().isEmpty()) inputQuantityBurger1.setText("0");
        if(inputQuantityBurger2.getText().isEmpty()) inputQuantityBurger2.setText("0");
        try{
            adminService.addProductBurg(inputNameProductBurgGet,inputNameProductBurgGet1,inputNameProductBurgGet2,
                    quantityNameProdBurg,quantityNameProdBurg1,quantityNameProdBurg2);
            clearInputs();


        } catch (SQLException | IOException e) {
            throw new CustomIOException(e.getMessage(),e);
        }

    }


    private void clearInputs(){

        //PRODUITS NORMAUX
        //listes
        inputNameProduct.getItems().clear();
        inputNameProduct1.getItems().clear();
        inputNameProduct2.getItems().clear();
        //input quantités
        inputQuantity.clear();
        inputQuantity1.clear();
        inputQuantity2.clear();

        //PRODUITS BURGERS
        //listes
        inputNameProductBurger.getItems().clear();
        inputNameProductBurger1.getItems().clear();
        inputNameProductBurger2.getItems().clear();
        //input quantités
        inputQuantityBurger.clear();
        inputQuantityBurger1.clear();
        inputQuantityBurger2.clear();




    }


}