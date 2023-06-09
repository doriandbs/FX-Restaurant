package services.interfaces.forms;

import models.administration.Users;

import java.io.IOException;
import java.sql.SQLException;

public interface IInscriptionService {
    boolean isUserExistInDatabase(Users utilisateur) throws SQLException, IOException;
    void insertNewUserIntoDatabase(Users utilisateur) throws IOException, SQLException;
}
