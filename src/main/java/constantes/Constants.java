/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package constantes;
public class Constants {
    private Constants() {
    }

    public static final String NOM_REC = "Nom requis";

    public static final String BADGE_REC = "Badge requis";
    public static final String PSW_REC = "Mot de passe requis";

    public static final String PSW_REGISTER = """
        Le mot de passe doit contenir au moins 8 caractères
        Au moins un chiffre
        Au moins une minuscule et une majuscule
        Au moins un caractère spécial et aucun espace""";

    public static final String CONN_SUCC = "CONNEXION REUSSIE";
    public static final String VERIF_CH = "veuillez rentrer tous les champs";
    public static final String  NOM_PSW_INCORRECT = "nom ou mot de passe incorrect";
    public static final String USER_NOT_FOUND = "Utilisateur inconnu, veuilez cliquer vous inscrire";
    public static final String USER_EXIST = "L'utilisateur existe déjà";

    public static final String USER_CREA = "Utilisateur créer";



}
