/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;

import exception.CustomIOException;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.CartPay;
import models.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MenuChooseController {
    Stage stage;
    Scene scene;

    @FXML
    GridPane gridPaneChoose;

    @FXML
    public void initialize() throws IOException {
        ajoutItemMenu();
    }

    private void ajoutItemMenu() throws FileNotFoundException {
        VBox productView10=productView(Product.PEPSIFORM);
        gridPaneChoose.add(productView10,0,0);
        VBox productView11=productView(Product.SPRITEFORM);
        gridPaneChoose.add(productView11,1,0);
        VBox productView12=productView(Product.EAUFORM);
        gridPaneChoose.add(productView12,2,0);
        VBox productView13=productView(Product.FANTAFORM);
        gridPaneChoose.add(productView13,3,0);
    }

    private VBox productView(Product product) throws FileNotFoundException {
        VBox layout= new VBox();
        layout.setAlignment(Pos.CENTER);
        FileInputStream input = new FileInputStream("src/main/resources/"+product.getImageFile());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Label productName = new Label(product.name());
        Label price = new Label(product.getPrice()+"€");
        Button addButton = new Button("ADD to cart");
        addButton.setUserData(product.name());

        addButton.setOnAction(event -> {
            Node source = (Node) event.getSource();
            String productName1 = (String)  source.getUserData();
            CartPay cart = CartPay.getInstance();
            cart.addProduct(productName1);
            addButton.setStyle("-fx-background-color: #00ff00");
            StackPane stackPane = new StackPane(new Label("added"));
            Scene popupScene = new Scene(stackPane, 50, 50);
            Stage popup = new Stage();
            popup.initStyle(StageStyle.UNDECORATED);
            stackPane.setAlignment(Pos.BOTTOM_CENTER);
            popup.setScene(popupScene);
            popup.show();

            PauseTransition wait = new PauseTransition(Duration.seconds(0.5));
            wait.setOnFinished(e -> {
                popup.close();
                addButton.setStyle("-fx-background-color: #e3ecb4");
            });
            wait.play();

    });
        layout.getChildren().addAll(imageView,productName,price,addButton);
        return layout;
}
    @FXML
    private void mappingBack(ActionEvent event) throws CustomIOException {
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
