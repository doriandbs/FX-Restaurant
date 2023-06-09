/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package models.administration;


import javafx.beans.property.*;

public class Employee {

    private final StringProperty name;
    private final StringProperty firstName;
    private final StringProperty dateBirth;
    private final StringProperty adresse;
    private final StringProperty numTel;
    private final StringProperty dateEmbauche;
    private final StringProperty badge;
    private final BooleanProperty isAdmin;
    private final IntegerProperty id;

    public Employee(Integer id, String name, String firstName, String badge, String adresse, String dateBirth, String numTel, String dateEmbauche, Boolean isAdmin) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.firstName = new SimpleStringProperty(firstName);
        this.dateBirth = new SimpleStringProperty(dateBirth);
        this.adresse = new SimpleStringProperty(adresse);
        this.dateEmbauche = new SimpleStringProperty(dateEmbauche);
        this.badge = new SimpleStringProperty(badge);
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
        this.numTel = new SimpleStringProperty(numTel);

    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getDateBirth() {
        return dateBirth.get();
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth.set(dateBirth);
    }

    public StringProperty dateBirthProperty() {
        return dateBirth;
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public StringProperty adresseProperty() {
        return adresse;
    }

    public String getNumTel() {
        return numTel.get();
    }

    public void setNumTel(String numTel) {
        this.numTel.set(numTel);
    }

    public StringProperty numTelProperty() {
        return numTel;
    }

    public String getDateEmbauche() {
        return dateEmbauche.get();
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche.set(dateEmbauche);
    }

    public StringProperty dateEmbaucheProperty() {
        return dateEmbauche;
    }

    public String getBadge() {
        return badge.get();
    }

    public void setBadge(String badge) {
        this.badge.set(badge);
    }

    public StringProperty badgeProperty() {
        return badge;
    }

    public boolean getIsAdmin() {
        return isAdmin.get();
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin.set(isAdmin);
    }

    public BooleanProperty isAdminProperty() {
        return isAdmin;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

}
