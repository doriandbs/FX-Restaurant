package models;

import models.products.AddProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddProductsTest {
    private AddProducts product;

    @BeforeEach
    void setUp() {
        product = new AddProducts(1, "TestProduct", "100", "50", "10", "2023-01-01", "2023-12-31");
    }

    @Test
    void testConstructor() {
        assertEquals(1, product.getId());
        assertEquals("TestProduct", product.getNameProducts());
        assertEquals("100", product.getPrice());
        assertEquals("50", product.getQuantity());
        assertEquals("10", product.getMinQuantity());
        assertEquals("2023-01-01", product.getDOP());
        assertEquals("2023-12-31", product.getBBD());
    }

    @Test
    void testPropertyAccessors() {
        assertEquals(1, product.idProperty().get());
        assertEquals("TestProduct", product.nameProductsProperty().get());
        assertEquals("100", product.priceProperty().get());
        assertEquals("50", product.quantityProperty().get());
        assertEquals("10", product.minQuantityProperty().get());
        assertEquals("2023-01-01", product.dopProperty().get());
        assertEquals("2023-12-31", product.bbdProperty().get());
    }

    @Test
    void testSetters() {
        product.setId(2);
        product.setNameProducts("NewProduct");
        product.setPrice("200");
        product.setQuantity("100");
        product.setMinQuantity("20");
        product.setDOP("2023-02-01");
        product.setBBD("2023-12-30");

        assertEquals(2, product.getId());
        assertEquals("NewProduct", product.getNameProducts());
        assertEquals("200", product.getPrice());
        assertEquals("100", product.getQuantity());
        assertEquals("20", product.getMinQuantity());
        assertEquals("2023-02-01", product.getDOP());
        assertEquals("2023-12-30", product.getBBD());
    }
}
