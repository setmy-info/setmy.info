package info.setmy.crypto.models;

import info.setmy.crypto.exceptions.CryptoException;
import java.util.Base64;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Holds the AES key and algorithm parameters for crypto operations.
 * Key must be 32 bytes (256-bit) for AES-256.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Getter
@RequiredArgsConstructor
public class CryptoConfig {

    public static final int KEY_SIZE = 32;

    private final byte[] key;

    /**
     * Create a CryptoConfig from a Base64-encoded AES-256 key string.
     * Compatible with the AI_KEY environment variable format used by the Python app.
     *
     * @param base64Key Base64-encoded 32-byte AES key
     * @return CryptoConfig instance
     */
    public static CryptoConfig fromBase64Key(final String base64Key) {
        if (base64Key == null || base64Key.isBlank()) {
            throw new CryptoException("Base64 key must not be null or blank");
        }
        final byte[] keyBytes = Base64.getDecoder().decode(base64Key.trim());
        if (keyBytes.length != KEY_SIZE) {
            throw new CryptoException(
                String.format("Key must be %d bytes (AES-256), got %d", KEY_SIZE, keyBytes.length)
            );
        }
        return new CryptoConfig(keyBytes);
    }
}
