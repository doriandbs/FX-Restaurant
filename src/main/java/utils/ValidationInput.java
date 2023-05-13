/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package utils;

public class ValidationInput {

    private ValidationInput() {
        //doNothing
    }

    public static boolean textFieldNull(String field) {
        return field.isBlank();
    }


    public static boolean passwordRegister(String field) {
        boolean dataLenght;
        dataLenght= !field.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
        return dataLenght;
    }

}

