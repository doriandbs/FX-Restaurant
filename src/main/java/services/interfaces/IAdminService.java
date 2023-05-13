package services.interfaces;

import exception.CustomIOException;
import models.AddProducts;
import models.Employee;

import java.util.List;

public interface IAdminService {
    List<Employee> getAllEmployees();
    void addProduct(AddProducts addProducts) throws CustomIOException;

}
