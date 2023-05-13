/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package models;

public class Users {
    private String name;

    private String password;
    private Boolean isAdmin;

    private String badge;


    public String getName() {
        return name;
    }

    public void setName(String setName) {
        name = setName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String setPassword) {
        password = setPassword;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String setBadge) {
        badge = setBadge;
    }



}
