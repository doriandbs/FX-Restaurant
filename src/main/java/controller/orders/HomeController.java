/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package controller.orders;

import exception.CustomIOException;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.cart.CartEntry;
import models.cart.CartPay;
import models.products.Product;
import services.interfaces.orders.IHomeService;
import services.orders.HomeServiceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static models.products.Product.CANTALBURGER;

public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    IHomeService homeService = new HomeServiceImpl();



    public static final String PADDING_5PX="-fx-padding:5px";

    @FXML
    public CheckBox checkEatIn;
    @FXML
    public CheckBox checkTakeAway;
    @FXML
    public TextArea noCmd;
    @FXML
    private Pane pnlMenu;
    @FXML
    private Pane pnlBurger;
    @FXML
    private Pane pnlBoisson;
    @FXML
    private Pane pnlDessert;
    @FXML
    private Pane pnlSupp;
    @FXML
    private Pane pnlCart;
    @FXML
    private ImageView imgMenu;
    @FXML
    private ImageView imgBurger;
    @FXML
    private ImageView imgDrink;
    @FXML
    private ImageView imgDessert;
    @FXML
    private ImageView imgSupp;
    @FXML
    private ImageView imgCart;

    @FXML
    private GridPane gridPaneSupp;
    @FXML
    private GridPane gridPaneBurger;
    @FXML
    private GridPane gridPaneMenu;
    @FXML
    private GridPane gridPaneBoisson;
    @FXML
    private GridPane gridPaneDessert;

    @FXML
    private VBox cartPane;
    @FXML
    private Label totalPriceLabel;

    Scene scene;
    Stage stage;

    @FXML
    public void initialize() throws IOException, SQLException {
        cleanPanels();
        ajoutItemMenu();
        majnumcommande();
        pnlMenu.toFront();

    }
    private void majnumcommande() throws SQLException, IOException {
        int lastCommandId = homeService.getLastCommandId();
        noCmd.setText(String.valueOf(lastCommandId + 1));
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
            if("MENU".equals(productName1)){
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/orders/menus/menuChooseBurger.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    try {
                        throw new CustomIOException("Error loading menuChooseBurger.fxml", e);
                    } catch (CustomIOException ex) {
                        logger.info(String.valueOf(e));
                    }
                }



            }else {
                popup.setScene(popupScene);
                popup.show();

                PauseTransition wait = new PauseTransition(Duration.seconds(0.5));
                wait.setOnFinished(e -> {
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
        gridPaneSupp.getChildren().clear();
        gridPaneBurger.getChildren().clear();
        gridPaneDessert.getChildren().clear();
        gridPaneBoisson.getChildren().clear();
        gridPaneMenu.getChildren().clear();
    }
    @FXML
    private void handleButtonAction(MouseEvent mouseEvent) throws FileNotFoundException {
        if (mouseEvent.getSource() == imgMenu) {
            cleanPanels();

            pnlMenu.toFront();

            ajoutItemMenu();

        } else if (mouseEvent.getSource() == imgBurger) {
            cleanPanels();

            pnlBurger.toFront();

            ajoutItemBurger();
            
        } else if (mouseEvent.getSource() == imgDrink) {
            cleanPanels();

            pnlBoisson.toFront();

            ajoutItemBoisson();
        } else if (mouseEvent.getSource() == imgDessert) {
            cleanPanels();
            pnlDessert.toFront();
            ajoutItemDessert();
        } else if (mouseEvent.getSource() == imgSupp) {
            cleanPanels();
            pnlSupp.toFront();
            ajoutItemSupp();
        }else if (mouseEvent.getSource() == imgCart){
            cleanPanels();
            logger.info("bien cliqué");
            pnlCart.toFront();
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
            throw new CustomIOException("Error loading login_page.fxml", e);
        }
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    public void sortByType(List<CartEntry> cartEntry) {
        Comparator<CartEntry> cartEntryComparator = new Comparator<>() {
            @Override
            public int compare(CartEntry entry1, CartEntry entry2) {
                // Comparez les types des entrées de panier
                return entry1.getProduct().getType().compareTo(entry2.getProduct().getType());
            }
        };

        Collections.sort(cartEntry, cartEntryComparator);
    }





    @FXML
    public void opencart() throws FileNotFoundException {
        List<CartEntry> entries = CartPay.getInstance().getEntries();
        cartPane.getChildren().clear();
        cartPane.setMaxHeight(1);
        if(entries.isEmpty()){
            Label empty = new Label("Cart Empty");
            cartPane.getChildren().add(empty);
        }else {
            Label notEmpty = new Label("Cart : ");
            cartPane.getChildren().add(notEmpty);
            for (CartEntry cartEntry : entries) {
                HBox productView = cartEntry(cartEntry);
                productView.getChildren().get(1).setStyle("-fx-font-size: 13px");
                cartPane.getChildren().add(productView);

            }
            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            cartPane.getChildren().add(separator);

            HBox totalView = totalView(CartPay.getInstance().calculateTotal());
            cartPane.getChildren().add(totalView);

        }
        sortByType(entries);

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
            } catch (SQLException e) {
                try {
                    throw new SQLException("Error sending the command", e);
                } catch (SQLException ex) {
                    logger.info(String.valueOf(e));
                }
            } catch (IOException e) {
                try {
                    throw new IOException("Error sending the command", e);
                } catch (IOException ex) {
                    logger.info(String.valueOf(e));
                }
            }
        });

        return layout;
    }

    private void envoyerCommande() throws SQLException, IOException {
        List<CartEntry> cartEntries = CartPay.getInstance().getEntries();

        // Effectuer les mises à jour de la base de données pour chaque CartEntry
        for (CartEntry cartEntry : cartEntries) {
            String productName = cartEntry.getProduct().name();
            int quantity = cartEntry.getQuantity();

            if(productName.contains("BURGER")){
                homeService.updateStockIngredientBurger(productName);
            } else{
                homeService.updateStock(productName, quantity);
            }
        }
        if (CartPay.getInstance().isTypeCartEatIn()) CartPay.getInstance().setTypeCartFinal("ON PLACE");
        else if(CartPay.getInstance().isTypeCartTakeAway()) CartPay.getInstance().setTypeCartFinal("TAKE AWAY");
        else CartPay.getInstance().setTypeCartFinal("NON RENSEIGNE");

        homeService.insertCommand();
        CartPay.getInstance().resetEntries();
        majnumcommande();
        cartPane.getChildren().clear();
    }

    @FXML
    private void changeCartType(ActionEvent event){
        if(event.getSource() == checkEatIn){
            checkTakeAway.setSelected(false);
            CartPay.getInstance().setTypeCartEatIn(checkEatIn.isSelected());
            CartPay.getInstance().setTypeCartTakeAway(checkTakeAway.isSelected());
        }else if(event.getSource() == checkTakeAway){
            checkEatIn.setSelected(false);
            CartPay.getInstance().setTypeCartEatIn(checkEatIn.isSelected());
            CartPay.getInstance().setTypeCartTakeAway(checkTakeAway.isSelected());
        }

    }




    private HBox cartEntry(CartEntry cartEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setMaxHeight(5);
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
        quantity.setStyle(PADDING_5PX);


        //price

        Label price = new Label(cartEntry.getProduct().getPrice()+"€");
        price.setStyle(PADDING_5PX);


        //BUTTON MENUS
        //ImageView viewM = new ImageView(String.valueOf(getClass().getClassLoader().getResource("img2/Common/remove.png")));
        Button buttonM = new Button("-");
        buttonM.setStyle(PADDING_5PX);
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
        buttonP.setStyle(PADDING_5PX);
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
        VBox productView3=productView(CANTALBURGER);
        gridPaneBurger.add(productView3,0,0);
        VBox productView4=productView(Product.CHICKENBURGER);
        gridPaneBurger.add(productView4,1,0);
        VBox productView5=productView(Product.VEGANBURGER);
        gridPaneBurger.add(productView5,2,0);
    }



    private void ajoutItemBoisson() throws FileNotFoundException {
        VBox productView6=productView(Product.PEPSI);
        gridPaneBoisson.add(productView6,0,0);
        VBox productView7=productView(Product.SPRITE);
        gridPaneBoisson.add(productView7,1,0);
        VBox productView8=productView(Product.FANTA);
        gridPaneBoisson.add(productView8,2,0);
        VBox productView9=productView(Product.EAU);
        gridPaneBoisson.add(productView9,3,0);
    }


    private void ajoutItemMenu() throws FileNotFoundException {
        VBox productView10=productView(Product.MENU);
        gridPaneMenu.add(productView10,0,0);
        /*VBox productView11=productView(Product.MENU2);
        gridPaneMenu.add(productView11,1,0);*/
    }


    private void ajoutItemDessert() throws FileNotFoundException {
        VBox productView12=productView(Product.DONUTS);
        gridPaneDessert.add(productView12,0,0);
        VBox productView13=productView(Product.MACARON);
        gridPaneDessert.add(productView13,1,0);
        VBox productView14=productView(Product.COOKIE);
        gridPaneDessert.add(productView14,2,0);
        VBox productView15=productView(Product.CAKE);
        gridPaneDessert.add(productView15,3,0);
    }


    private void ajoutItemSupp() throws FileNotFoundException {
        VBox productView16=productView(Product.CHIPS);
        gridPaneSupp.add(productView16,0,0);
        VBox productView17=productView(Product.MAIS);
        gridPaneSupp.add(productView17,1,0);
    }

}

/*} else if (cartEntry.getProduct() == Product.MENU) {
                // Mise à jour des ingrédients pour le MENU
                int burgerIngredientID1 = 1; // Remplacer par l'ID réel de l'ingrédient 1 du burger pour le MENU
                int burgerIngredientID2 = 2; // Remplacer par l'ID réel de l'ingrédient 2 du burger pour le MENU
                int burgerIngredientID3 = 3; // Remplacer par l'ID réel de l'ingrédient 3 du burger pour le MENU

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
            } else if (cartEntry.getProduct() == Product.MENU2) {
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
                updateBurgerIngredientStmt3.executeUpdate();*/