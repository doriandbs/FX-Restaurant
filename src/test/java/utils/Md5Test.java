package utils;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Md5Test {

    @Test
    void testGenerateHash() throws NoSuchAlgorithmException {
        assertEquals("0E9C20D9B237AECC65DE77A491061BE5", Md5.generateHash("27c7cf400229103e00c6d8830029e29b"));
    }


    @Test
    void testBytestoStringHex() {
        assertEquals("4158415841584158", Md5.bytestoStringHex("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));
    }
}

