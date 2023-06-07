/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller;

import exception.CustomIOException;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    public void initialize() throws IOException, CustomIOException {
        CartPay.getInstance().setCptFormule(0);
    }


    protected VBox productView(Product product) throws FileNotFoundException{
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

            Parent root;
            try {
                    if(CartPay.getInstance().getCptFormule()==0) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/menuChooseBurger.fxml")));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else if(CartPay.getInstance().getCptFormule()==1){
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/menuChooseAccompagnement.fxml")));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }else if(CartPay.getInstance().getCptFormule()==2 ){
                        System.out.println(productName1);
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/menuChooseBoisson.fxml")));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }else if(CartPay.getInstance().getCptFormule()==3 ){
                        System.out.println(productName1);
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/menu.fxml")));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else if(CartPay.getInstance().getCptFormule()==4 ){
                        System.out.println(productName1);
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/home.fxml")));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        layout.getChildren().addAll(imageView,productName,price,addButton);
        return layout;
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
