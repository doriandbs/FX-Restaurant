package controller;

import bdd.DatabaseSingleton;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.CartEntry;
import models.CartPay;
import models.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static constantes.SQLConstants.INSERTCOMMANDE;

public class HomeController {
    public TextArea no_cmd;
    @FXML
    private Pane pnl_menu, pnl_burger, pnl_boisson, pnl_dessert, pnl_supp, pnl_cart;
    @FXML
    private ImageView img_menu, img_burger, img_drink, img_dessert, img_supp, img_cart;

    @FXML
    private GridPane GridPaneSupp, GridPaneBurger, GridPaneMenu, GridPaneBoisson, GridPaneDessert;

    @FXML
    private VBox cartPane;

    private Label totalPriceLabel;

    Scene scene;
    Stage stage;

    @FXML
    public void initialize() throws IOException, SQLException {
        cleanPanels();
        ajoutItemMenu();
        majnumcommande();
        pnl_menu.toFront();

    }
    private void majnumcommande() throws SQLException, IOException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement selectIdCmd = db.prepareStatement("SELECT ID FROM COMMANDES ORDER BY id DESC LIMIT 1");
        ResultSet selectRes = selectIdCmd.executeQuery();
        if (selectRes.next()) {
            int id = selectRes.getInt("ID");
            no_cmd.setText(String.valueOf(id+1));
        }
        db.close();
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
            if("MENU1".equals(productName1) || "MENU2".equals(productName1)){
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/menuChoose.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else {
                popup.setScene(popupScene);
                popup.show();

                PauseTransition wait = new PauseTransition(Duration.seconds(0.5));
                wait.setOnFinished((e) -> {
                    popup.close();
                    addButton.setStyle("-fx-background-color: #e3ecb4");
                });
                wait.play();
            }
        });
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
    private void handleButtonAction(MouseEvent mouseEvent) throws FileNotFoundException {
        if (mouseEvent.getSource() == img_menu) {
            cleanPanels();

            pnl_menu.toFront();

            ajoutItemMenu();

        } else if (mouseEvent.getSource() == img_burger) {
            cleanPanels();

            pnl_burger.toFront();

            ajoutItemBurger();
            
        } else if (mouseEvent.getSource() == img_drink) {
            cleanPanels();

            pnl_boisson.toFront();

            ajoutItemBoisson();
        } else if (mouseEvent.getSource() == img_dessert) {
            cleanPanels();
            pnl_dessert.toFront();
            ajoutItemDessert();
        } else if (mouseEvent.getSource() == img_supp) {
            cleanPanels();
            pnl_supp.toFront();
            ajoutItemSupp();
        }else if (mouseEvent.getSource() == img_cart){
            cleanPanels();
            System.out.println("bien cliqué");
            pnl_cart.toFront();
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
    public void Exit() {
        Platform.exit();
    }





    @FXML
    public void opencart() throws FileNotFoundException {
        List<CartEntry> entries = CartPay.getInstance().getEntries();
        cartPane.getChildren().clear();
        if(entries.isEmpty()){
            Label empty = new Label("Cart Empty");
            cartPane.getChildren().add(empty);
        }else {
            Label notEmpty = new Label("Cart : ");
            cartPane.getChildren().add(notEmpty);
            for (CartEntry cartEntry : entries) {
                HBox productView = cartEntry(cartEntry);
                cartPane.getChildren().add(productView);
            }
            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            cartPane.getChildren().add(separator);

            HBox totalView = totalView(CartPay.getInstance().calculateTotal());
            cartPane.getChildren().add(totalView);
        }
    }



    private HBox totalView(float totalPrice){
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        Label total = new Label("TOTAL : ");
        Button sendCart = new Button("ENVOYER LA COMMANDE");
        total.setStyle("-fx-font-size:15pt;");
        this.totalPriceLabel = new Label(String.valueOf(totalPrice));
        layout.getChildren().addAll(total,this.totalPriceLabel, sendCart);
        sendCart.setOnAction(event -> {
            try {
                envoyerCommande();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        return layout;
    }

    private void envoyerCommande() throws SQLException, IOException {
        List<CartEntry> cartEntries = CartPay.getInstance().getEntries();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();

        // Effectuer les mises à jour de la base de données pour chaque CartEntry
        for (CartEntry cartEntry : cartEntries) {
            String productName = cartEntry.getProduct().name();
            int quantity = cartEntry.getQuantity();
            System.out.println(quantity + productName);
            // REQUETE D'UPDATE DU PRODUIT ET DE LA QUANTITE
/*
            if (cartEntry.getProduct() == Product.MENU1) {
                // Mise à jour des ingrédients pour le MENU1
                int burgerIngredientID1 = 1; // Remplacer par l'ID réel de l'ingrédient 1 du burger pour le MENU1
                int burgerIngredientID2 = 2; // Remplacer par l'ID réel de l'ingrédient 2 du burger pour le MENU1
                int burgerIngredientID3 = 3; // Remplacer par l'ID réel de l'ingrédient 3 du burger pour le MENU1

                String updateBurgerIngredientQuery1 = "UPDATE INGREDIENTS SET stock_ingredient = stock_ingredient - 1 WHERE id_ingredient = ?";
                PreparedStatement updateBurgerIngredientStmt1 = db.prepareStatement(updateBurgerIngredientQuery1);
                updateBurgerIngredientStmt1.setInt(1, burgerIngredientID1);
                updateBurgerIngredientStmt1.executeUpdate();

                String updateBurgerIngredientQuery2 = "UPDATE INGREDIENTS SET stock_ingredient = stock_ingredient - 1 WHERE id_ingredient = ?";
                PreparedStatement updateBurgerIngredientStmt2 = db.prepareStatement(updateBurgerIngredientQuery2);
                updateBurgerIngredientStmt2.setInt(1, burgerIngredientID2);
                updateBurgerIngredientStmt2.executeUpdate();

                String updateBurgerIngredientQuery3 = "UPDATE INGREDIENTS SET stock_ingredient = stock_ingredient - 1 WHERE id_ingredient = ?";
                PreparedStatement updateBurgerIngredientStmt3 = db.prepareStatement(updateBurgerIngredientQuery3);
                updateBurgerIngredientStmt3.setInt(1, burgerIngredientID3);
                updateBurgerIngredientStmt3.executeUpdate();
            }
            else if (cartEntry.getProduct() == Product.MENU2) {
                // Mise à jour des ingrédients pour le MENU2
                int burgerIngredientID1 = 4; // Remplacer par l'ID réel de l'ingrédient 1 du burger pour le MENU2
                int burgerIngredientID2 = 5; // Remplacer par l'ID réel de l'ingrédient 2 du burger pour le MENU2
                int burgerIngredientID3 = 6; // Remplacer par l'ID réel de l'ingrédient 3 du burger pour le MENU2

                String updateBurgerIngredientQuery1 = "UPDATE INGREDIENTS SET stock_ingredient = stock_ingredient - 1 WHERE id_ingredient = ?";
                PreparedStatement updateBurgerIngredientStmt1 = db.prepareStatement(updateBurgerIngredientQuery1);
                updateBurgerIngredientStmt1.setInt(1, burgerIngredientID1);
                updateBurgerIngredientStmt1.executeUpdate();

                String updateBurgerIngredientQuery3 = "UPDATE INGREDIENTS SET stock_ingredient = stock_ingredient - 1 WHERE id_ingredient = ?";
                PreparedStatement updateBurgerIngredientStmt3 = db.prepareStatement(updateBurgerIngredientQuery3);
                updateBurgerIngredientStmt3.setInt(1, burgerIngredientID3);
                updateBurgerIngredientStmt3.executeUpdate();
            }

 */
        }

        PreparedStatement insertEmp = db.prepareStatement(INSERTCOMMANDE);
        insertEmp.setString(1, String.valueOf(LocalDateTime. now()));
        insertEmp.setString(2, String.valueOf(CartPay.getInstance().calculateTotal()));
        insertEmp.executeUpdate();
        insertEmp.close();
        db.close();
        majnumcommande();
        cartPane.getChildren().clear();
    }




    private HBox cartEntry(CartEntry cartEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);
        FileInputStream input = new FileInputStream("src/main/resources/"+cartEntry.getProduct().getImageFile());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);


        //NAME OF PRODUCT
        Label productName = new Label(cartEntry.getProduct().name());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");

        //QUANTITY

        Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
        quantity.setStyle("-fx-padding:5px");


        //price

        Label price = new Label(String.valueOf(cartEntry.getProduct().getPrice())+"€");
        price.setStyle("-fx-padding:5px");


        //BUTTON MENUS
        //ImageView viewM = new ImageView(String.valueOf(getClass().getClassLoader().getResource("img2/Common/remove.png")));
        Button buttonM = new Button("-");
        buttonM.setStyle("-fx-padding:5px");
        buttonM.setUserData(cartEntry.getProduct().name());
        buttonM.setOnAction(e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            CartPay.getInstance().removeProduct(name);
            quantity.setText(String.valueOf(CartPay.getInstance().getQuantity(name)));
            price.setText(cartEntry.getProduct().getPrice()*CartPay.getInstance().getQuantity(name)+"€");
            if(quantity.getText().equals("0")){
                layout.getChildren().clear();
            }
            this.totalPriceLabel.setText(String.valueOf(CartPay.getInstance().calculateTotal()));
            if(CartPay.getInstance().calculateTotal()==0){
                cartPane.getChildren().clear();
                CartPay.getInstance().resetEntries();
            }

        });
        //buttonM.setGraphic(viewM);

        //BUTTON PLUS
        //ImageView viewP = new ImageView(String.valueOf(getClass().getClassLoader().getResource("img2/Common/plus.png")));
        Button buttonP = new Button("+");
        buttonP.setStyle("-fx-padding:5px");
        buttonP.setUserData(cartEntry.getProduct().name());
        buttonP.setOnAction(e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            CartPay.getInstance().addProduct(name);
            quantity.setText(String.valueOf(CartPay.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText(String.valueOf(CartPay.getInstance().calculateTotal()));
            price.setText(cartEntry.getProduct().getPrice()*CartPay.getInstance().getQuantity(name)+"€");
        });
        //buttonP.setGraphic(viewP);


        //PRICE

        layout.getChildren().addAll(imageView,productName,buttonP,quantity,buttonM,price);

        return layout;

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