package services.orders;

import bdd.DatabaseSingleton;
import models.cart.CartPay;
import services.interfaces.orders.IHomeService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import static constantes.SQLConstants.*;

public class HomeServiceImpl implements IHomeService {
    private static final Logger logger = Logger.getLogger(HomeServiceImpl.class.getName());

    @Override
    public int getLastCommandId() throws SQLException, IOException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement selectIdCmd = db.prepareStatement(SELECTLASTIDORDERS);
        ResultSet selectRes = selectIdCmd.executeQuery();
        int id = -1;
        if (selectRes.next()) {
            id = selectRes.getInt("ID");
        }
        db.close();
        return id;
    }



    @Override
    public void updateStock(String productName, int quantity) throws SQLException, IOException {
        System.out.println(productName);
        String productForm = productName.replace("FORM","");
        System.out.println(productForm);
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement updateStock = db.prepareStatement(UPDATESTOCK);
        updateStock.setInt(1, quantity);
        updateStock.setString(2, productForm);
        updateStock.executeUpdate();
        updateStock.close();
        db.close();
    }

    @Override
    public void updateStockIngredientBurger(String productName) throws SQLException, IOException {
        String viande="";
        String fromage="";
        if(productName.contains("BURGER")){
            switch (productName) {
                case "CANTALBURGER", "CANTALBURGERFORM" -> {
                    viande = "Steak";
                    fromage = "FromageCantal";
                }
                case "CHICKENBURGER", "CHICKENBURGERFORM" -> {
                    viande = "Poulet";
                    fromage = "FromageChevre";
                }
                case "VEGANBURGER", "VEGANBURGERFORM" -> {
                    viande = "SteakVegan";
                    fromage = "FromageMoza";
                }
                default -> logger.info("erreur");
            }
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement updateStockIngredientBurger = db.prepareStatement(UPDATESTOCKINGREDIENTBURGER);
            updateStockIngredientBurger.setString(1, viande);
            updateStockIngredientBurger.setString(2, fromage);
            updateStockIngredientBurger.setString(3, viande);
            updateStockIngredientBurger.setString(4, fromage);
            updateStockIngredientBurger.executeUpdate();
            updateStockIngredientBurger.close();
            db.close();
        }
    }

    @Override
    public void insertCommand() throws SQLException, IOException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement insertCmd = db.prepareStatement(INSERTCOMMANDE);
        insertCmd.setString(1, String.valueOf(LocalDateTime. now()));
        insertCmd.setString(2, String.valueOf(CartPay.getInstance().calculateTotal()));
        insertCmd.setString(3,String.valueOf(CartPay.getInstance().getTypeCartFinal()));
        insertCmd.executeUpdate();
        insertCmd.close();
        db.close();
    }

}

