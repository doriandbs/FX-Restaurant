package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationInputTest {

    @Test
    void testTextFieldNull() {
        assertFalse(ValidationInput.textFieldNull("Field"));
        assertTrue(ValidationInput.textFieldNull(""));
    }

    @Test
    void testPasswordRegister() {
        assertTrue(ValidationInput.passwordRegister("Field"));
    }
}

