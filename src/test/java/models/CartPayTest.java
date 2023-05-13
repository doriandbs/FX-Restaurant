package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartPayTest {
    private CartPay cartPay;
    private Product product;

    @BeforeEach
    void setUp() {
        cartPay = CartPay.getInstance();
        product = Product.MENU1;
    }

    @Test
    void testAddProduct() {
        cartPay.addProduct(product.name());
        assertEquals(1, cartPay.getQuantity(product.name()));
    }

    @Test
    void testRemoveProduct() {
        cartPay.addProduct(product.name());
        cartPay.removeProduct(product.name());
        assertEquals(0, cartPay.getQuantity(product.name()));
    }

    @Test
    void testGetQuantity() {
        cartPay.addProduct(product.name());
        cartPay.addProduct(product.name());
        assertEquals(3, cartPay.getQuantity(product.name()));
        cartPay.removeProduct(product.name());
        cartPay.removeProduct(product.name());
        cartPay.removeProduct(product.name());
        assertEquals(0, cartPay.getQuantity(product.name()));

    }

    @Test
    void testCalculateTotal() {
        cartPay.addProduct(product.name());
        cartPay.addProduct(product.name());
        assertEquals(3 * product.getPrice(), cartPay.calculateTotal());
    }

    @Test
    void testGetEntries() {
        cartPay.addProduct(product.name());
        List<CartEntry> entries = cartPay.getEntries();
        assertEquals(1, entries.size());
        assertEquals(entries.get(0).getProduct(), product);
        assertEquals(1, entries.get(0).getQuantity());
    }


    @Test
    void testResetEntries() {
        CartPay instance = CartPay.getInstance();
        instance.resetEntries();
        assertTrue(instance.getEntries().isEmpty());
    }

    @Test
    void testGetQuantityOfNonexistentProduct() {
        assertEquals(0, cartPay.getQuantity("Nonexistent product"));
    }
}
