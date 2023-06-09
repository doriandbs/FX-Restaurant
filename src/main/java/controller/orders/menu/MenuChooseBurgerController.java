package controller.orders.menu;

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
import models.cart.CartPay;
import models.products.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MenuChooseBurgerController {
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
        CartPay.getInstance().setCptFormule(1);
    }

    private void ajoutItemMenu() throws FileNotFoundException {
        menuChooseController = new MenuChooseController();
        VBox productView10=menuChooseController.productView(Product.CANTALBURGERFORM);
        gridPaneChoose.add(productView10,0,0);
        VBox productView11=menuChooseController.productView(Product.CHICKENBURGERFORM);
        gridPaneChoose.add(productView11,1,0);
        VBox productView12=menuChooseController.productView(Product.VEGANBURGERFORM);
        gridPaneChoose.add(productView12,2,0);

    }

    @FXML
    protected void mappingBack(ActionEvent event) throws CustomIOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/orders/home.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CustomIOException("Erreur lors du chargement de la page", e);
        }

    }
}
