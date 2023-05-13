/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package utils;

public class ValidationInput {

    public static boolean textFieldNull(String field) {
        return field.isBlank();
    }


    public static boolean PasswordRegister(String field) {
        boolean DataLenght = false;
        if (!field.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
            DataLenght = true;
        }
        return DataLenght;
    }

}

