package services.administration;

import bdd.DatabaseSingleton;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.administration.ChiffreAffaire;
import models.administration.Employee;
import services.interfaces.administration.IAdminService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static constantes.SQLConstants.*;

public class AdminServiceImpl implements IAdminService {
    private static final Logger logger = Logger.getLogger(AdminServiceImpl.class.getName());

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement selectEmp = db.prepareStatement(SELECTEMPLOYEE);
            ResultSet resultSet = selectEmp.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("BADGE"),
                        resultSet.getString("ADRESSE"),
                        resultSet.getString("DATEBIRTH"),
                        resultSet.getString("NUMTEL"),
                        resultSet.getString("DATEHIRING"),
                        resultSet.getBoolean("ISADMIN")
                ));
            }
            selectEmp.close();
            //db.close();
        } catch (SQLException | IOException e) {
            logger.info(String.valueOf(e));
        }
        return employees;
    }
/**
    @Override
    public List<ChiffreAffaire> getAllChiffreAffaire() {
        List<ChiffreAffaire> ca = new ArrayList<>();
        StringProperty month = null;
        StringProperty totalMontant = null;
        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            PreparedStatement selectCA = db.prepareStatement("SELECT MONTH, TOTALMONTANT FROM CHIFFREAFFAIRE");
            ResultSet resultSet = selectCA.executeQuery();
            while (resultSet.next()) {
                ca.add(new ChiffreAffaire(
                        // month1, month2, month3, month4, month5, month6, month7, month8, month9, month10, month11, month12, resultSet.getDouble("TOTALMONTANT")
                        month, resultSet.getDouble("TOTALMONTANT")
                ));
            }

            selectCA.close();
            db.close();
        } catch (SQLException e) {
            logger.info(String.valueOf(e));
        }
        return ca;
    }
 */
    @Override
    public List<ChiffreAffaire> getAllChiffreAffaire() {
        List<ChiffreAffaire> ca = new ArrayList<>();
        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement selectCA = db.prepareStatement("SELECT MONTH, TOTALMONTANT FROM ChiffreAffaire");
            ResultSet resultSet = selectCA.executeQuery();
            while (resultSet.next()) {
                ca.add(new ChiffreAffaire(
                        resultSet.getString("MONTH"),
                        resultSet.getDouble("TotalMontant")
                ));

            }
            selectCA.close();
            //db.close();
        } catch (SQLException | IOException e) {
            logger.info(String.valueOf(e));
        }
        return ca;
    }
    @Override
    public int countEmpl() throws SQLException {
        int count= 0;
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        PreparedStatement psc = db.prepareStatement(COUNTEMPLOYEE);
        ResultSet rsc = psc.executeQuery();
        if (rsc.next()) {
            count = rsc.getInt("recordCount");
        }

        rsc.close();
        //db.close();
        return count;
    }
    @Override
    public void addProductBurg(String inputNameProductBurgGet,String inputNameProductBurgGet1, String inputNameProductBurgGet2
                               ,String quantityNameProdBurg, String quantityNameProdBurg1, String quantityNameProdBurg2) throws  SQLException, IOException {

        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement updateStockBurg = db.prepareStatement(UPDATEINGBURG);
        updateStockBurg.setString(1, quantityNameProdBurg);
        updateStockBurg.setString(2, inputNameProductBurgGet);
        updateStockBurg.executeUpdate();
        updateStockBurg.close();
        PreparedStatement updateStockBurg1 = db.prepareStatement(UPDATEINGBURG);
        updateStockBurg1.setString(1, quantityNameProdBurg1);
        updateStockBurg1.setString(2, inputNameProductBurgGet1);
        updateStockBurg1.executeUpdate();
        updateStockBurg1.close();
        PreparedStatement updateStockBurg2 = db.prepareStatement(UPDATEINGBURG);
        updateStockBurg2.setString(1, quantityNameProdBurg2);
        updateStockBurg2.setString(2, inputNameProductBurgGet2);
        updateStockBurg2.executeUpdate();
        updateStockBurg2.close();
        //db.close();
    }


    @Override
    public void addProduct(String inputNameProductGet, String inputNameProduct1Get,String inputNameProduct2Get,
                           String quantityNameProd, String quantityNameProd1, String quantityNameProd2) throws  SQLException, IOException {

        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement updateStockBurg = db.prepareStatement(UPDATESTOCKADMIN);
        updateStockBurg.setString(1, quantityNameProd);
        updateStockBurg.setString(2,inputNameProductGet );
        updateStockBurg.executeUpdate();
        updateStockBurg.close();
        PreparedStatement updateStockBurg1 = db.prepareStatement(UPDATESTOCKADMIN);
        updateStockBurg1.setString(1, quantityNameProd1);
        updateStockBurg1.setString(2,inputNameProduct1Get );
        updateStockBurg1.executeUpdate();
        updateStockBurg1.close();
        PreparedStatement updateStockBurg2 = db.prepareStatement(UPDATESTOCKADMIN);
        updateStockBurg2.setString(1, quantityNameProd2);
        updateStockBurg2.setString(2, inputNameProduct2Get);
        updateStockBurg2.executeUpdate();
        updateStockBurg2.close();
        //db.close();
    }

    @Override
    public ObservableList<String> loadDataListProduct() throws SQLException, IOException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement selectProdName = db.prepareStatement("SELECT NAME FROM STOCKS;");
        ResultSet resultSet = selectProdName.executeQuery();
        ObservableList<String> items = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String donnees = resultSet.getString("NAME");
            items.add(donnees);
        }

        selectProdName.close();
        //db.close();
        return items;
    }

    @Override
    public ObservableList<String> loadDataListProductBurg() throws SQLException, IOException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement selectProdName = db.prepareStatement("SELECT NOM_INGREDIENT FROM INGREDIENTS");
        ResultSet resultSet = selectProdName.executeQuery();
        ObservableList<String> items = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String donnees = resultSet.getString("NOM_INGREDIENT");
            items.add(donnees);
        }
        selectProdName.close();
        //db.close();
        return items;
    }



}
