package utils;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SHATest {

    @Test
    void testGenerateHash() throws NoSuchAlgorithmException {
        assertEquals("868C17ADCB2267042ACFBE7B6D0DA3892441BB3004F1518ACD73716E8E8A3DC8", SHA.generateHash("27c7cf400229103e00c6d8830029e29b"));
    }


    @Test
    void testBytestoStringHex() {
        assertEquals("4158415841584158", SHA.bytestoStringHex("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));
    }
}

