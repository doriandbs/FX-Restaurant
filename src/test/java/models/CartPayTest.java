package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartPayTest {

    private CartPay cartPay;

    @BeforeEach
    void setUp() {
        cartPay = CartPay.getInstance();
    }

    @Test
    void testAddProduct() {
        cartPay.addProduct("CANTALBURGER");
        assertEquals(3, cartPay.getQuantity("CANTALBURGER"));
    }

    @Test
    void testRemoveProduct() {
        cartPay.addProduct("CANTALBURGER");
        cartPay.removeProduct("CANTALBURGER");
        assertEquals(0, cartPay.getQuantity("CANTALBURGER"));
    }

    @Test
    void testGetQuantity() {
        cartPay.addProduct("CANTALBURGER");
        cartPay.addProduct("CANTALBURGER");
        assertEquals(2, cartPay.getQuantity("CANTALBURGER"));
    }

    @Test
    void testCalculateTotal() {
        cartPay.addProduct("CANTALBURGER"); 
        cartPay.addProduct("CHIPS"); 
        assertEquals(29.5, cartPay.calculateTotal());
    }

    @Test
    void testGetEntries() {
        cartPay.addProduct("CANTALBURGER");
        assertEquals(1, cartPay.getEntries().size());
    }

    @Test
    void testResetEntries() {
        cartPay.addProduct("CANTALBURGER");
        cartPay.resetEntries();
        assertEquals(0, cartPay.getEntries().size());
    }
    @Test
    void testValueOf() {
        assertEquals("img2/Home/Menu/menu_burger.png", Product.valueOf("MENU").getImageFile());
    }
}
