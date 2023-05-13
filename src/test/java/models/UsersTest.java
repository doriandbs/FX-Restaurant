package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {
    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
    }

    @Test
    void testName() {
        String testName = "John Doe";
        user.setName(testName);
        assertEquals(testName, user.getName());
    }

    @Test
    void testPassword() {
        String testPassword = "password123";
        user.setPassword(testPassword);
        assertEquals(testPassword, user.getPassword());
    }

    @Test
    void testIsAdmin() {
        user.setIsAdmin(true);
        assertTrue(user.getIsAdmin());

        user.setIsAdmin(false);
        assertFalse(user.getIsAdmin());
    }

    @Test
    void testBadge() {
        String testBadge = "123456";
        user.setBadge(testBadge);
        assertEquals(testBadge, user.getBadge());
    }
}
