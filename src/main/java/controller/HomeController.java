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
    private GridPane GridPaneSupp, GridPaneBurger, GridPaneMenu, GridPaneBoisson, GridPaneDessert;
    Scene scene;
    Stage stage;

    @FXML
    public void initialize() throws FileNotFoundException {
        cleanPanels();
        ajoutItemMenu();
        pnl_menu.toFront();

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

    private void cleanPanels(){
        GridPaneSupp.getChildren().clear();
        GridPaneBurger.getChildren().clear();
        GridPaneDessert.getChildren().clear();
        GridPaneBoisson.getChildren().clear();
        GridPaneMenu.getChildren().clear();
    }
    @FXML
    private void handleButtonAction(MouseEvent mouseDragEvent) throws FileNotFoundException {
        if (mouseDragEvent.getSource() == img_menu) {
            cleanPanels();

            pnl_menu.toFront();

            ajoutItemMenu();

        } else if (mouseDragEvent.getSource() == img_burger) {
            cleanPanels();

            pnl_burger.toFront();

            ajoutItemBurger();
            
        } else if (mouseDragEvent.getSource() == img_drink) {
            cleanPanels();

            pnl_boisson.toFront();

            ajoutItemBoisson();
        } else if (mouseDragEvent.getSource() == img_dessert) {
            cleanPanels();
            pnl_dessert.toFront();
            ajoutItemDessert();
        } else if (mouseDragEvent.getSource() == img_supp) {
            cleanPanels();
            pnl_supp.toFront();
            ajoutItemSupp();

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





    private void ajoutItemBurger() throws FileNotFoundException {
        VBox productView3=productView(Product.CANTALBURGER);
        GridPaneBurger.add(productView3,0,0);
        VBox productView4=productView(Product.CHICKENBURGER);
        GridPaneBurger.add(productView4,1,0);
        VBox productView5=productView(Product.VEGANBURGER);
        GridPaneBurger.add(productView5,2,0);
    }



    private void ajoutItemBoisson() throws FileNotFoundException {
        VBox productView6=productView(Product.PEPSI);
        GridPaneBoisson.add(productView6,0,0);
        VBox productView7=productView(Product.SPRITE);
        GridPaneBoisson.add(productView7,1,0);
        VBox productView8=productView(Product.FANTA);
        GridPaneBoisson.add(productView8,2,0);
        VBox productView9=productView(Product.EAU);
        GridPaneBoisson.add(productView9,3,0);
    }


    private void ajoutItemMenu() throws FileNotFoundException {
        VBox productView10=productView(Product.MENU1);
        GridPaneMenu.add(productView10,0,0);
        VBox productView11=productView(Product.MENU2);
        GridPaneMenu.add(productView11,1,0);
    }


    private void ajoutItemDessert() throws FileNotFoundException {
        VBox productView12=productView(Product.DONUTS);
        GridPaneDessert.add(productView12,0,0);
        VBox productView13=productView(Product.MACARON);
        GridPaneDessert.add(productView13,1,0);
        VBox productView14=productView(Product.COOKIE);
        GridPaneDessert.add(productView14,2,0);
        VBox productView15=productView(Product.CAKE);
        GridPaneDessert.add(productView15,3,0);
    }


    private void ajoutItemSupp() throws FileNotFoundException {
        VBox productView16=productView(Product.CHIPS);
        GridPaneSupp.add(productView16,0,0);
        VBox productView17=productView(Product.MAIS);
        GridPaneSupp.add(productView17,1,0);
    }

}