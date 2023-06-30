/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package constantes;

public class SQLConstants {
    public static final String SELECTUSERS = "SELECT * FROM user WHERE NOM = ? AND BADGE = ? AND PASSWORD = ? AND ISADMIN = ?";
    public static final String INSERTUSER = "INSERT INTO user(NOM,BADGE,PASSWORD,ISADMIN) VALUES(?,?,?,?)";

    public static final String SELECTUSER = "SELECT * FROM user WHERE BADGE = ? AND PASSWORD = ? ";

    public static final String COUNTEMPLOYEE = "SELECT COUNT(*) AS recordCount FROM employee";
    public static final String SELECTEMPLOYEE = "SELECT * FROM employee";
    public static final String INSERTEMPLOYEE = "INSERT INTO employee(NAME,BADGE,ISADMIN,FIRSTNAME,ADRESSE,DATEBIRTH, DATEHIRING, NUMTEL) VALUES(?,?,?,?,?,?,?,?)";
    public static final String INSERTCOMMANDE ="INSERT INTO COMMANDES(DATE,TOTAL,TYPE,COMMENTARY) VALUES(?,?,?,?)";

    public static final String UPDATESTOCK ="UPDATE STOCKS SET QUANTITY = QUANTITY - ? WHERE NAME = ? ";
    public static final String UPDATESTOCKINGREDIENTBURGER ="UPDATE INGREDIENTS SET STOCK_INGREDIENT = "
            + "CASE "
            + "WHEN nom_ingredient = 'Pains' THEN STOCK_INGREDIENT - 2 "
            + "WHEN nom_ingredient = 'Salade' THEN STOCK_INGREDIENT - 1 "
            +"WHEN nom_ingredient = 'Tomate' THEN STOCK_INGREDIENT - 1 "
            +"WHEN nom_ingredient = 'Oignons' THEN STOCK_INGREDIENT - 1 "
            +"WHEN nom_ingredient = ? THEN STOCK_INGREDIENT - 1 "
            +"WHEN nom_ingredient = ? THEN STOCK_INGREDIENT - 1 "
            + "ELSE STOCK_INGREDIENT "
            + "END "
            + "WHERE nom_ingredient IN ('Pains', 'Salade','Tomates','Oignons',?,?)";

    public static final String SELECTLASTIDORDERS="SELECT ID FROM COMMANDES ORDER BY id DESC LIMIT 1";

    public static final String UPDATEINGBURG="UPDATE INGREDIENTS SET STOCK_INGREDIENT=STOCK_INGREDIENT + ?  WHERE NOM_INGREDIENT = ?";

    public static final String UPDATESTOCKADMIN ="UPDATE STOCKS SET QUANTITY = QUANTITY + ? WHERE NAME = ? ";

    public static final String GETALLCA ="SELECT MONTH, TOTALMONTANT FROM ChiffreAffaire";

    public static final String DATALISTPRODUCT = "SELECT NAME FROM STOCKS";
    public static final String DATALISTBURGER = "SELECT NOM_INGREDIENT FROM INGREDIENTS";


    private SQLConstants() {
    }
}
