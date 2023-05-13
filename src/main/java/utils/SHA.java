package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
        private SHA() {
                //doNothing
        }

        private static final  char[] hexArray = "0123456789ABCDEF".toCharArray();

        public static String generateHash(String pass) throws NoSuchAlgorithmException {
                MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                algorithm.reset();
                byte[] hash = algorithm.digest(pass.getBytes());
                return bytestoStringHex(hash);

        }

        public static String bytestoStringHex(byte[] bytes) {
                char[] hexChars = new char[bytes.length * 2];
                for (int j = 0; j < bytes.length; j++) {
                        int v = bytes[j] & 0xFF;
                        hexChars[j * 2] = hexArray[v >>> 4];
                        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
                }
                return new String(hexChars);
        }

}
        
        
