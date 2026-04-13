package info.setmy.crypto.services;

import info.setmy.crypto.exceptions.CryptoException;
import info.setmy.crypto.models.CryptoConfig;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AES-256-CBC symmetric encryption and decryption service.
 *
 * Wire format (binary): IV (16 bytes) || ciphertext
 * Wire format (Base64): Base64( IV || ciphertext )
 *
 * Compatible with the Python app/crypto.py implementation:
 *   encrypt returns Base64(iv + ciphertext) + newline
 *   decrypt expects Base64(iv + ciphertext), strips whitespace
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Slf4j
@RequiredArgsConstructor
public class AesCryptoService {

    static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    static final int IV_SIZE = 16;

    private final CryptoConfig config;

    /**
     * Encrypt plaintext bytes.
     *
     * @param plaintext raw bytes to encrypt
     * @return raw bytes: IV (16) || ciphertext
     */
    public byte[] encrypt(final byte[] plaintext) {
        try {
            final byte[] iv = generateIv();
            final Cipher cipher = buildCipher(Cipher.ENCRYPT_MODE, iv);
            final byte[] ciphertext = cipher.doFinal(plaintext);
            return concat(iv, ciphertext);
        } catch (IllegalBlockSizeException | BadPaddingException ex) {
            throw new CryptoException("Encryption failed", ex);
        }
    }

    /**
     * Encrypt plaintext bytes and return Base64-encoded result.
     * Output matches the Python encrypt() format (without the trailing newline).
     *
     * @param plaintext raw bytes to encrypt
     * @return Base64-encoded string of IV || ciphertext
     */
    public String encryptToBase64(final byte[] plaintext) {
        return Base64.getEncoder().encodeToString(encrypt(plaintext));
    }

    /**
     * Decrypt raw bytes (IV || ciphertext).
     *
     * @param ivAndCiphertext byte array where first 16 bytes are the IV
     * @return decrypted plaintext bytes
     */
    public byte[] decrypt(final byte[] ivAndCiphertext) {
        try {
            final byte[] iv = Arrays.copyOfRange(ivAndCiphertext, 0, IV_SIZE);
            final byte[] ciphertext = Arrays.copyOfRange(ivAndCiphertext, IV_SIZE, ivAndCiphertext.length);
            final Cipher cipher = buildCipher(Cipher.DECRYPT_MODE, iv);
            return cipher.doFinal(ciphertext);
        } catch (IllegalBlockSizeException | BadPaddingException ex) {
            throw new CryptoException("Decryption failed", ex);
        }
    }

    /**
     * Decrypt a Base64-encoded string produced by encryptToBase64() or Python encrypt().
     * Strips surrounding whitespace before decoding, compatible with Python output.
     *
     * @param base64Data Base64-encoded IV || ciphertext (may include trailing newline)
     * @return decrypted plaintext bytes
     */
    public byte[] decryptFromBase64(final String base64Data) {
        final byte[] ivAndCiphertext = Base64.getDecoder().decode(base64Data.strip());
        return decrypt(ivAndCiphertext);
    }

    private byte[] generateIv() {
        final byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    private Cipher buildCipher(final int mode, final byte[] iv) {
        try {
            final SecretKeySpec keySpec = new SecretKeySpec(config.getKey(), "AES");
            final IvParameterSpec ivSpec = new IvParameterSpec(iv);
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(mode, keySpec, ivSpec);
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            throw new CryptoException("Cipher algorithm not available: " + ALGORITHM, ex);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException ex) {
            throw new CryptoException("Invalid key or IV", ex);
        }
    }

    private byte[] concat(final byte[] a, final byte[] b) {
        final byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
