/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */

package models.products;

public enum Product {
    //MENUS
    MENU("assets/Home/Menu/menu_burger.png",8.90f,"MENU"),
    //MENU2("img2/Home/Menu/menu_burger_1.png",7.90f,"MENU"),

   //BURGERS
    CANTALBURGER("assets/Home/Burger/burger_steack.png",6.50f,"BURGER"),
    CHICKENBURGER("assets/Home/Burger/burger_poulet.png",6.90f,"BURGER"),
    VEGANBURGER("assets/Home/Burger/burger_vegetalien.png",6.90f,"BURGER"),
    CHIPS("assets/Home/Fries/supplement_frite.png",3.50f,"ACCOMPAGNEMENT"),
    MAIS("assets/Home/Fries/supplement_mais.png",2.50f,"ACCOMPAGNEMENT"),

    //BOISSONS
    PEPSI("assets/Home/Drink/soda_pepsi.png",2.30f,"BOISSON"),
    FANTA("assets/Home/Drink/soda_fanta.png",2.30f,"BOISSON"),
    SPRITE("assets/Home/Drink/soda_sprite.png",2.30f,"BOISSON"),
    EAU("assets/Home/Drink/bouteille_eau.png",1.50f,"BOISSON"),


    PEPSIFORM("assets/Home/Drink/soda_pepsi.png",0,"BOISSON_FORMULE"),
    FANTAFORM("assets/Home/Drink/soda_fanta.png",0,"BOISSON_FORMULE"),
    SPRITEFORM("assets/Home/Drink/soda_sprite.png",0,"BOISSON_FORMULE"),
    EAUFORM("assets/Home/Drink/bouteille_eau.png",0,"BOISSON_FORMULE"),



    CANTALBURGERFORM("assets/Home/Burger/burger_steack.png",0,"BURGER_FORMULE"),
    CHICKENBURGERFORM("assets/Home/Burger/burger_poulet.png",0,"BURGER_FORMULE"),
    VEGANBURGERFORM("assets/Home/Burger/burger_vegetalien.png",0,"BURGER_FORMULE"),

    COOKIEFORM("assets/Home/Dessert/dessert_cookie.png",0,"DESSERT_FORMULE"),
    DONUTSFORM("assets/Home/Dessert/dessert_donuts.png",0,"DESSERT_FORMULE"),
    CAKEFORM("assets/Home/Dessert/dessert_gateau.png",0,"DESSERT_FORMULE"),
    MACARONFORM("assets/Home/Dessert/dessert_macaron.png",0,"DESSERT_FORMULE"),

    //DESSERTS
    COOKIE("assets/Home/Dessert/dessert_cookie.png",2.00f,"DESSERT"),
    DONUTS("assets/Home/Dessert/dessert_donuts.png",1.50f,"DESSERT"),
    CAKE("assets/Home/Dessert/dessert_gateau.png",1.50f,"DESSERT"),
    MACARON("assets/Home/Dessert/dessert_macaron.png",2.50f,"DESSERT"),

    CHIPSFORM("assets/Home/Fries/supplement_frite.png",0,"ACCOMPAGNEMENT_FORMULE"),
    MAISFORM("assets/Home/Fries/supplement_mais.png",0,"ACCOMPAGNEMENT_FORMULE");


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
