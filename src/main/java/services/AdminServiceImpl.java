package services;

import bdd.DatabaseSingleton;
import exception.CustomIOException;
import models.AddProducts;
import models.Employee;
import services.interfaces.IAdminService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static constantes.SQLConstants.SELECTEMPLOYEE;

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
            db.close();
        } catch (SQLException | IOException e) {
            logger.info(String.valueOf(e));
        }
        return employees;
    }

    @Override
    public void addProduct(AddProducts addProducts) throws CustomIOException {
        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement addProd = db.prepareStatement("");//TODO add request for insertProduct
            addProd.setString(1, addProducts.getNameProducts());
            addProd.setString(2, addProducts.getPrice());
            addProd.setString(3, addProducts.getQuantity());
            addProd.setString(4, addProducts.getMinQuantity());
            addProd.setObject(5, addProducts.getDOP());
            addProd.setObject(6, addProducts.getBBD());

            addProd.executeUpdate();
            addProd.close();
            db.close();
        } catch (SQLException | IOException e) {
            throw new CustomIOException("Erreur SQL", e);
        }
    }


}
