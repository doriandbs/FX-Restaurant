module FX.Restaurant {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.j;
    exports main to javafx.graphics;
    exports controller to javafx.fxml;
    opens controller to javafx.fxml;
    opens models to javafx.base;
    exports bdd to javafx.graphics;
}