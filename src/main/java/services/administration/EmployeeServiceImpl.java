package services.administration;

import bdd.DatabaseSingleton;
import models.administration.Employee;
import services.interfaces.administration.IEmployeeService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import static constantes.SQLConstants.INSERTEMPLOYEE;

public class EmployeeServiceImpl implements IEmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());


    @Override
    public void saveEmployee(Employee employee) {
        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement insertEmp = db.prepareStatement(INSERTEMPLOYEE);
            insertEmp.setString(1, employee.getName());
            insertEmp.setString(2, employee.getBadge());
            insertEmp.setBoolean(3, employee.getIsAdmin());
            insertEmp.setString(4, employee.getFirstName());
            insertEmp.setString(5, employee.getAdresse());
            insertEmp.setString(6, employee.getDateBirth());
            insertEmp.setString(7, employee.getDateEmbauche());
            insertEmp.setString(8, employee.getNumTel());

            insertEmp.executeUpdate();
            insertEmp.close();
            db.close();
        } catch (SQLException | IOException e) {
            logger.info(String.valueOf(e));
        }
    }
}
