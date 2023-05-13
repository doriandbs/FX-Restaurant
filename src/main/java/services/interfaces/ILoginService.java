package services.interfaces;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface ILoginService {
    boolean authenticateUser(String username, String password) throws NoSuchAlgorithmException, SQLException, IOException;
    boolean isAdminUser(String badge,String password) throws SQLException, IOException, NoSuchAlgorithmException;

}
