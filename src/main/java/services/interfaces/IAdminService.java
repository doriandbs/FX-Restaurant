package services.interfaces;

import javafx.collections.ObservableList;
import models.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IAdminService {
    List<Employee> getAllEmployees();
    void addProductBurg(String quantityNameProdBurg, String quantityNameProdBurg1, String quantityNameProdBurg2,
                        String inputNameProductBurgGet, String inputNameProductBurgGet1, String inputNameProductBurgGet2) throws SQLException, IOException;

    void addProduct(String inputNameProductGet, String inputNameProduct1Get,String inputNameProduct2Get,
    String quantityNameProd, String quantityNameProd1, String quantityNameProd2) throws SQLException, IOException;

    ObservableList<String> loadDataListProduct() throws SQLException, IOException;
    ObservableList<String> loadDataListProductBurg() throws SQLException, IOException;
    int countEmpl() throws SQLException;
}
