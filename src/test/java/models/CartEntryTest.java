package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartEntryTest {
    private CartEntry cartEntry;
    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.MENU1; // Using one of the Product enum constants
        cartEntry = new CartEntry(product, 1);
    }

    @Test
    void testGetProduct() {
        assertEquals(product, cartEntry.getProduct());
    }

    @Test
    void testSetProduct() {
        Product newProduct = Product.MENU2; // Using another Product enum constant
        cartEntry.setProduct(newProduct);
        assertEquals(newProduct, cartEntry.getProduct());
    }

    @Test
    void testGetQuantity() {
        assertEquals(1, cartEntry.getQuantity());
    }

    @Test
    void testSetQuantity() {
        cartEntry.setQuantity(5);
        assertEquals(5, cartEntry.getQuantity());
    }

    @Test
    void testDecreaseQuantityNotNegative() {
        cartEntry.setQuantity(0);
        cartEntry.decreaseQuantity();
        assertEquals(0, cartEntry.getQuantity());
    }
    @Test
    void testIncreaseQuantity() {
        cartEntry.increaseQuantity();
        assertEquals(2, cartEntry.getQuantity());
    }

    @Test
    void testDecreaseQuantity() {
        cartEntry.decreaseQuantity();
        assertEquals(0, cartEntry.getQuantity());
    }
}
