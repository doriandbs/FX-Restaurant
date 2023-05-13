package services;

import bdd.DatabaseSingleton;
import services.interfaces.ILoginService;
import utils.Md5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constantes.SQLConstants.SELECTUSER;

public class LoginServiceImpl implements ILoginService {
    @Override
    public boolean authenticateUser(String badge, String password) throws NoSuchAlgorithmException, SQLException, IOException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement selectUser = db.prepareStatement(SELECTUSER);
        selectUser.setString(1, badge);
        selectUser.setString(2, Md5.generateHash(password));
        ResultSet resultSet = selectUser.executeQuery();
        boolean userExists = resultSet.next();
        resultSet.close();
        selectUser.close();
        db.close();
        return userExists;
    }
    @Override
    public boolean isAdminUser(String badge, String password) throws SQLException, IOException, NoSuchAlgorithmException {
        boolean isAdmin = false;
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement selectUser = db.prepareStatement(SELECTUSER);
        selectUser.setString(1, badge);
        selectUser.setString(2, Md5.generateHash(password));
        ResultSet resultSet = selectUser.executeQuery();
        while(resultSet.next()) if (resultSet.getInt("ISADMIN") == 1)  isAdmin = true;

        selectUser.close();
        db.close();

        return isAdmin;
    }
}
