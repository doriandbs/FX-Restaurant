package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    public void start(Stage stage) throws Exception {
        try {
            AnchorPane pan = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/login_page.fxml")));
            Scene scene = new Scene(pan);
            stage.setTitle("Gestionnaire de restaurant");
            //stage.initStyle(StageStyle.UNDECORATED); Enleve la croix de l'écran
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("eerreur " + e);
        }
    }
    public static void main(String[] args){
        launch(args);
    }

}





