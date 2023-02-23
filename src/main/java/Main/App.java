package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class App extends Application {
    public void start(Stage stage) {
        try {
            AnchorPane pan = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/login_page.fxml")));
            Scene scene = new Scene(pan);
            stage.setTitle("Frusty's App");
            stage.getIcons().add(new Image("img2/icon.png"));
            stage.initStyle(StageStyle.UNDECORATED);// Enleve la croix de l'écran
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        launch(args);
    }


}





