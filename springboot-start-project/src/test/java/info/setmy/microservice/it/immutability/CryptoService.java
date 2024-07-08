package info.setmy.microservice.it.immutability;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoService {

    private static final CryptoService cryptoService = new CryptoService();

    private static final String SALT = "MHVZqgAlO9+jY0rCbn1ACFAxaX23lgrf";
    private static final String ALGORITM = "AES";

    public static CryptoService getInstance() {
        return cryptoService;
    }

    public String encrypt(String text) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SALT.getBytes(), ALGORITM);
            Cipher cipher = Cipher.getInstance(ALGORITM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cryptedTextBytes = cipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(cryptedTextBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    public String decrypt(String text) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SALT.getBytes(), ALGORITM);
            Cipher cipher = Cipher.getInstance(ALGORITM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(text));
            return new String(decryptedText);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public SecretKey defaultKey() throws Exception {
        return generateKey(256);
    }

    public SecretKey generateKey(int length) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITM);
        keyGenerator.init(length);
        return keyGenerator.generateKey();
    }
}
