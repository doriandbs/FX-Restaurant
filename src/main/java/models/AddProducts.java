package models;

import javafx.beans.property.*;

public class AddProducts {

    private StringProperty Name_products;
    private StringProperty Price;
    private StringProperty Quantity;
    private StringProperty MinQuantity;
    private ObjectProperty<Object> DOP;
    private ObjectProperty<Object> BBD;

    private IntegerProperty id;


    public AddProducts(Integer Id, String Name_products, String Price, String Quantity, String MinQuantity, Object DOP, Object BBD) {
        this.id = new SimpleIntegerProperty(Id);
        this.Name_products = new SimpleStringProperty(Name_products);
        this.Price = new SimpleStringProperty(Price);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.MinQuantity = new SimpleStringProperty(MinQuantity);
        this.DOP = new SimpleObjectProperty<>(DOP);
        this.BBD = new SimpleObjectProperty<>(BBD);
    }

    public String getName_products() {
        return Name_products.get();
    }

    public StringProperty name_productsProperty() {
        return Name_products;
    }

    public void setName_products(String name_products) {
        this.Name_products.set(name_products);
    }

    public String getPrice() {
        return Price.get();
    }

    public StringProperty priceProperty() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price.set(price);
    }

    public String getQuantity() {
        return Quantity.get();
    }

    public StringProperty quantityProperty() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        this.Quantity.set(quantity);
    }

    public String getMinQuantity() {
        return MinQuantity.get();
    }

    public StringProperty minQuantityProperty() {
        return MinQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.MinQuantity.set(minQuantity);
    }

    public Object getDOP() {
        return DOP.get();
    }

    public ObjectProperty<Object> DOPProperty() {
        return DOP;
    }

    public void setDOP(Object DOP) {
        this.DOP.set(DOP);
    }

    public Object getBBD() {
        return BBD.get();
    }

    public ObjectProperty<Object> BBDProperty() {
        return BBD;
    }

    public void setBBD(Object BBD) {
        this.BBD.set(BBD);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
