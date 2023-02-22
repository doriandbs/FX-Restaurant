/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */

package models;

public enum Product {
    //MENUS
    MENU1("img2/Home/Menu/menu_burger.png",8.90f),
    MENU2("img2/Home/Menu/menu_burger_1.png",7.90f),

   //BURGERS
    CANTALBURGER("img2/Home/Burger/burger_steack.png",6.50f),
    CHICKENBURGER("img2/Home/Burger/burger_poulet.png",6.90f),
    VEGANBURGER("img2/Home/Burger/burger_vegetalien.png",6.90f),
    CHIPS("img2/Home/Fries/supplement_frite.png",3.50f),
    MAIS("img2/Home/Fries/supplement_mais.png",2.50f),

    //BOISSONS
    PEPSI("img2/Home/Drink/soda_pepsi.png",2.30f),
    FANTA("img2/Home/Drink/soda_fanta.png",2.30f),
    SPRITE("img2/Home/Drink/soda_sprite.png",2.30f),
    EAU("img2/Home/Drink/bouteille_eau.png",1.50f),

    //DESSERTS
    COOKIE("img2/Home/Dessert/dessert_cookie.png",2.00f),
    DONUTS("img2/Home/Dessert/dessert_donuts.png",1.50f),
    CAKE("img2/Home/Dessert/dessert_gateau.png",1.50f),
    MACARON("img2/Home/Dessert/dessert_macaron.png",2.50f);






    private String imageFile;
    private float price;

    Product(String imageFile, float price) {
        this.imageFile = imageFile;
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
