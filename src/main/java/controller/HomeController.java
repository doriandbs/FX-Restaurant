package main.java.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {
    @FXML
    private Pane pnl_menu, pnl_burger, pnl_boisson, pnl_dessert, pnl_supp;
    @FXML
    private ImageView img_menu, img_burger, img_drink, img_dessert, img_supp;

    Scene scene;
    Stage stage;

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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/Views/login_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}