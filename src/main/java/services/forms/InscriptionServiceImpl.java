package services.forms;

import bdd.DatabaseSingleton;
import models.administration.Users;
import services.interfaces.forms.IInscriptionService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import static constantes.SQLConstants.INSERTUSER;
import static constantes.SQLConstants.SELECTUSERS;

public class InscriptionServiceImpl implements IInscriptionService {
    private static final Logger logger = Logger.getLogger(InscriptionServiceImpl.class.getName());



    @Override
    public boolean isUserExistInDatabase(Users utilisateur) throws SQLException, IOException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement selectUsers = db.prepareStatement(SELECTUSERS);
        selectUsers.setString(1, utilisateur.getName());
        selectUsers.setString(2, utilisateur.getBadge());
        selectUsers.setString(3, utilisateur.getPassword());
        selectUsers.setBoolean(4, utilisateur.getIsAdmin());
        boolean userExist = selectUsers.executeQuery().next();
        selectUsers.close();
        db.close();
        return userExist;
    }

    @Override
    public void insertNewUserIntoDatabase(Users utilisateur) throws IOException, SQLException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        PreparedStatement insertUser = db.prepareStatement(INSERTUSER);
        insertUser.setString(1, utilisateur.getName());
        insertUser.setString(2, utilisateur.getBadge());
        insertUser.setString(3, utilisateur.getPassword());
        insertUser.setBoolean(4, utilisateur.getIsAdmin());
        int n = insertUser.executeUpdate();
        if (n == 1) {
            logger.info("Requête d'insertion de l'utilisateur bien effectuée, NOM : " + utilisateur.getName() +
                    " BADGE : " + utilisateur.getBadge() + " ADMINISTRATEUR : " + utilisateur.getIsAdmin());
        }
        insertUser.close();
        db.close();
    }
}

