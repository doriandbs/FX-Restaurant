package main.java.models;


import javafx.beans.property.*;

public class Employee {

    private StringProperty Name;
    private StringProperty FirstName;
    private StringProperty DateBirth;
    private StringProperty Adresse;
    private StringProperty NumTel;
    private StringProperty DateEmbauche;
    private StringProperty Badge;
    private BooleanProperty IsAdmin;
    private IntegerProperty id;

    public Employee(Integer Id, String Name, String FirstName, String Badge, String Adresse, String DateBirth, String NumTel, String DateEmbauche, Boolean IsAdmin) {
        this.id = new SimpleIntegerProperty(Id);
        this.Name = new SimpleStringProperty(Name);
        this.FirstName = new SimpleStringProperty(FirstName);
        this.DateBirth = new SimpleStringProperty(DateBirth);
        this.Adresse = new SimpleStringProperty(Adresse);
        this.DateEmbauche = new SimpleStringProperty(DateEmbauche);
        this.Badge = new SimpleStringProperty(Badge);
        this.IsAdmin = new SimpleBooleanProperty(IsAdmin);
        this.NumTel = new SimpleStringProperty(NumTel);

    }

    public String getName() {
        return Name.get();
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public String getFirstName() {
        return FirstName.get();
    }

    public void setFirstName(String firstName) {
        this.FirstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return FirstName;
    }

    public String getDateBirth() {
        return DateBirth.get();
    }

    public void setDateBirth(String dateBirth) {
        this.DateBirth.set(dateBirth);
    }

    public StringProperty dateBirthProperty() {
        return DateBirth;
    }

    public String getAdresse() {
        return Adresse.get();
    }

    public void setAdresse(String adresse) {
        this.Adresse.set(adresse);
    }

    public StringProperty adresseProperty() {
        return Adresse;
    }

    public String getNumTel() {
        return NumTel.get();
    }

    public void setNumTel(String numTel) {
        this.NumTel.set(numTel);
    }

    public StringProperty numTelProperty() {
        return NumTel;
    }

    public String getDateEmbauche() {
        return DateEmbauche.get();
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.DateEmbauche.set(dateEmbauche);
    }

    public StringProperty dateEmbaucheProperty() {
        return DateEmbauche;
    }

    public String getBadge() {
        return Badge.get();
    }

    public void setBadge(String badge) {
        this.Badge.set(badge);
    }

    public StringProperty badgeProperty() {
        return Badge;
    }

    public boolean getIsAdmin() {
        return IsAdmin.get();
    }

    public void setIsAdmin(boolean isAdmin) {
        this.IsAdmin.set(isAdmin);
    }

    public BooleanProperty isAdminProperty() {
        return IsAdmin;
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
