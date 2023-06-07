/*
 * Copyright (c) 2023. Créé par Dorian Dubois - Kevin Gac - Kevin Lompo
 */

package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class CartPay {

    private boolean typeCartTakeAway;
    private boolean typeCartEatIn;

    private String typeCartFinal;

    private int cptFormule;

    private static CartPay instance;
    public static CartPay getInstance(){
        if(instance==null){
            instance = new CartPay();
        }
        return instance;
    }

    private final Map<String,CartEntry> entries;
    
     public CartPay(){
         this.entries= new HashMap<>();
     }

     public void addProduct(String productName){
         CartEntry productEntry= entries.get(productName.toUpperCase());
         if(productEntry!=null){
             productEntry.increaseQuantity();
         }else{
             Product product = Product.valueOf(productName);
             CartEntry entry = new CartEntry(product, 1);
             entries.put(productName.toUpperCase(),entry);
         }
     }

    public void removeProduct(String productName) {
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if (productEntry != null) {
            productEntry.decreaseQuantity();
            if (productEntry.getQuantity() == 0) {
                entries.remove(productName.toUpperCase());
            }
        }
    }

     public int getQuantity(String productName){
         CartEntry entry = entries.get(productName.toUpperCase());
         if(entry!=null){
             return entry.getQuantity();
         }
         return 0;
     }

     public float calculateTotal(){
         float total=0;
         for(CartEntry entry : entries.values()){
             float entryCost = entry.getProduct().getPrice()*entry.getQuantity();
             total += entryCost;
         }
         total = (float)(Math.ceil(total * 100.0) / 100.0);
         return total;
     }

     public List<CartEntry> getEntries(){
         return new ArrayList<>(entries.values());
     }
    public void resetEntries() {
        entries.clear();
    }




}
