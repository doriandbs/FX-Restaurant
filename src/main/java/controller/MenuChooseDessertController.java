package controller;

import exception.CustomIOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CartPay;
import models.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MenuChooseDessertController {
    public Button btn_back;
    Stage stage;
    Scene scene;
    @Autowired
    MenuChooseController menuChooseController;
    @FXML
    GridPane gridPaneChoose;
    @FXML
    public void initialize() throws IOException {
        ajoutItemMenu();
        CartPay.getInstance().setCptFormule(4);
    }

    private void ajoutItemMenu() throws FileNotFoundException {
        menuChooseController = new MenuChooseController();
        VBox productView10=menuChooseController.productView(Product.COOKIEFORM);
        gridPaneChoose.add(productView10,0,0);
        VBox productView11=menuChooseController.productView(Product.CAKEFORM);
        gridPaneChoose.add(productView11,1,0);
        VBox productView12=menuChooseController.productView(Product.MACARONFORM);
        gridPaneChoose.add(productView12,2,0);
        VBox productView13=menuChooseController.productView(Product.DONUTSFORM);
        gridPaneChoose.add(productView13,3,0);

    }


    @FXML
    protected void mappingBack(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/home.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur lors du chargement de la page", e);
        }

    }
}
