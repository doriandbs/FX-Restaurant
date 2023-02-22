package controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import models.Product;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class HomeController {
    @FXML
    private Pane pnl_menu, pnl_burger, pnl_boisson, pnl_dessert, pnl_supp;
    @FXML
    private ImageView img_menu, img_burger, img_drink, img_dessert, img_supp;

    @FXML
    private Button ajout1, ajout2;
    @FXML
    private GridPane GridPaneSupp, GridPaneMenu;
    Scene scene;
    Stage stage;

    @FXML
    public void initialize() throws FileNotFoundException {
        GridPaneSupp.getChildren().clear();
        GridPaneMenu.getChildren().clear();

        VBox productView1 = productView(Product.CHIPS) ;
        GridPaneSupp.add(productView1,0,0);

        VBox productView2=productView(Product.MAIS);
        GridPaneSupp.add(productView2,1,0);
        VBox productView3=productView(Product.CANTALBURGER);
        GridPaneMenu.add(productView3,0,0);
        VBox productView4=productView(Product.CHICKENBURGER);
        GridPaneMenu.add(productView4,1,0);
        VBox productView5=productView(Product.VEGANBURGER);
        GridPaneMenu.add(productView5,2,0);
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
        layout.getChildren().addAll(imageView,productName,price,addButton);
        return layout;
    }
    @FXML
    private void handleButtonAction(MouseEvent mouseDragEvent) {
        if (mouseDragEvent.getSource() == img_menu) {
            pnl_menu.toFront();
            System.out.println("Test bien appuyé");
        } else if (mouseDragEvent.getSource() == img_burger) {
            pnl_burger.toFront();
        } else if (mouseDragEvent.getSource() == img_drink) {
            pnl_boisson.toFront();
        } else if (mouseDragEvent.getSource() == img_dessert) {
            pnl_dessert.toFront();
        } else if (mouseDragEvent.getSource() == img_supp) {
            pnl_supp.toFront();
        }
    }

    public void MappingLogout(ActionEvent event) {
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
    public void Exit(ActionEvent event) {
        Platform.exit();
    }

    public void add(ActionEvent event){

    }


}