/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */
package models.products;

import javafx.beans.property.*;

public class AddProducts {

    private final StringProperty nameProducts;
    private final StringProperty price;
    private final StringProperty quantity;
    private final StringProperty minQuantity;
    private final ObjectProperty<Object> dop;
    private final ObjectProperty<Object> bbd;

    private final IntegerProperty id;


    public AddProducts(Integer id, String nameProducts, String price, String quantity, String minQuantity, Object dop, Object bbd) {
        this.id = new SimpleIntegerProperty(id);
        this.nameProducts = new SimpleStringProperty(nameProducts);
        this.price = new SimpleStringProperty(price);
        this.quantity = new SimpleStringProperty(quantity);
        this.minQuantity = new SimpleStringProperty(minQuantity);
        this.dop = new SimpleObjectProperty<>(dop);
        this.bbd = new SimpleObjectProperty<>(bbd);
    }

    public String getNameProducts() {
        return nameProducts.get();
    }

    public StringProperty nameProductsProperty() {
        return nameProducts;
    }

    public void setNameProducts(String nameProducts) {
        this.nameProducts.set(nameProducts);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getMinQuantity() {
        return minQuantity.get();
    }

    public StringProperty minQuantityProperty() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity.set(minQuantity);
    }

    public Object getDOP() {
        return dop.get();
    }

    public ObjectProperty<Object> dopProperty() {
        return dop;
    }

    public void setDOP(Object dop) {
        this.dop.set(dop);
    }

    public Object getBBD() {
        return bbd.get();
    }

    public ObjectProperty<Object> bbdProperty() {
        return bbd;
    }

    public void setBBD(Object bbd) {
        this.bbd.set(bbd);
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
