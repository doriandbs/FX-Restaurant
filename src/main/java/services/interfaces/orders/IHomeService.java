package services.interfaces.orders;

import java.io.IOException;
import java.sql.SQLException;

public interface IHomeService {
    int getLastCommandId() throws SQLException, IOException;
    void updateStock(String productName, int quantity) throws SQLException, IOException;
    void updateStockIngredientBurger(String productName) throws SQLException, IOException;
    void insertCommand(String commentaire) throws SQLException, IOException;
}

