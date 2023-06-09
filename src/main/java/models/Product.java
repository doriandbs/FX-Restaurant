/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */

package models;

public enum Product {
    //MENUS
    MENU("img2/Home/Menu/menu_burger.png",8.90f,"MENU"),
    //MENU2("img2/Home/Menu/menu_burger_1.png",7.90f,"MENU"),

   //BURGERS
    CANTALBURGER("img2/Home/Burger/burger_steack.png",6.50f,"BURGER"),
    CHICKENBURGER("img2/Home/Burger/burger_poulet.png",6.90f,"BURGER"),
    VEGANBURGER("img2/Home/Burger/burger_vegetalien.png",6.90f,"BURGER"),
    CHIPS("img2/Home/Fries/supplement_frite.png",3.50f,"ACCOMPAGNEMENT"),
    MAIS("img2/Home/Fries/supplement_mais.png",2.50f,"ACCOMPAGNEMENT"),

    //BOISSONS
    PEPSI("img2/Home/Drink/soda_pepsi.png",2.30f,"BOISSON"),
    FANTA("img2/Home/Drink/soda_fanta.png",2.30f,"BOISSON"),
    SPRITE("img2/Home/Drink/soda_sprite.png",2.30f,"BOISSON"),
    EAU("img2/Home/Drink/bouteille_eau.png",1.50f,"BOISSON"),


    PEPSIFORM("img2/Home/Drink/soda_pepsi.png",0,"BOISSON_FORMULE"),
    FANTAFORM("img2/Home/Drink/soda_fanta.png",0,"BOISSON_FORMULE"),
    SPRITEFORM("img2/Home/Drink/soda_sprite.png",0,"BOISSON_FORMULE"),
    EAUFORM("img2/Home/Drink/bouteille_eau.png",0,"BOISSON_FORMULE"),



    CANTALBURGERFORM("img2/Home/Burger/burger_steack.png",0,"BURGER_FORMULE"),
    CHICKENBURGERFORM("img2/Home/Burger/burger_poulet.png",0,"BURGER_FORMULE"),
    VEGANBURGERFORM("img2/Home/Burger/burger_vegetalien.png",0,"BURGER_FORMULE"),

    COOKIEFORM("img2/Home/Dessert/dessert_cookie.png",0,"DESSERT_FORMULE"),
    DONUTSFORM("img2/Home/Dessert/dessert_donuts.png",0,"DESSERT_FORMULE"),
    CAKEFORM("img2/Home/Dessert/dessert_gateau.png",0,"DESSERT_FORMULE"),
    MACARONFORM("img2/Home/Dessert/dessert_macaron.png",0,"DESSERT_FORMULE"),

    //DESSERTS
    COOKIE("img2/Home/Dessert/dessert_cookie.png",2.00f,"DESSERT"),
    DONUTS("img2/Home/Dessert/dessert_donuts.png",1.50f,"DESSERT"),
    CAKE("img2/Home/Dessert/dessert_gateau.png",1.50f,"DESSERT"),
    MACARON("img2/Home/Dessert/dessert_macaron.png",2.50f,"DESSERT"),

    CHIPSFORM("img2/Home/Fries/supplement_frite.png",0,"ACCOMPAGNEMENT_FORMULE"),
    MAISFORM("img2/Home/Fries/supplement_mais.png",0,"ACCOMPAGNEMENT_FORMULE");


    public int getCptFormule() {
        return cptFormule;
    }

 public String getType() {
  return type;
 }

 public void setType(String type) {
  type = type;
 }

 private final String type;

    public void setCptFormule(int cptFormule) {
        this.cptFormule = cptFormule;
    }

    private int cptFormule;
    private final String imageFile;
    private final float price;

    Product(String imageFile, float price, String type) {
        this.imageFile = imageFile;
        this.price = price;
        this.type = type;
    }

    public String getImageFile() {
        return imageFile;
    }



    public float getPrice() {
        return price;
    }

}
