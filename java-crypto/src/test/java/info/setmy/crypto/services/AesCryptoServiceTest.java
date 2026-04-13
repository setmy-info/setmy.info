package info.setmy.crypto.services;

import info.setmy.crypto.exceptions.CryptoException;
import info.setmy.crypto.models.CryptoConfig;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
class AesCryptoServiceTest {

    private AesCryptoService service;

    @BeforeEach
    void setUp() {
        final byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        service = new AesCryptoService(new CryptoConfig(key));
    }

    @Test
    void encrypt_returnsIvPlusCiphertext() {
        final byte[] plaintext = "hello world".getBytes(StandardCharsets.UTF_8);

        final byte[] result = service.encrypt(plaintext);

        assertThat(result).hasSizeGreaterThan(AesCryptoService.IV_SIZE);
    }

    @Test
    void encrypt_prependsIvOf16Bytes() {
        final byte[] plaintext = "test".getBytes(StandardCharsets.UTF_8);

        final byte[] result = service.encrypt(plaintext);

        // result = IV (16) + ciphertext (padded to AES block multiple)
        assertThat(result.length).isGreaterThanOrEqualTo(AesCryptoService.IV_SIZE + 16);
    }

    @Test
    void encrypt_producesDifferentCiphertextEachTime() {
        final byte[] plaintext = "same content".getBytes(StandardCharsets.UTF_8);

        final byte[] first = service.encrypt(plaintext);
        final byte[] second = service.encrypt(plaintext);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    void decrypt_roundtrip() {
        final byte[] plaintext = "Hello, encrypted world!".getBytes(StandardCharsets.UTF_8);

        final byte[] encrypted = service.encrypt(plaintext);
        final byte[] decrypted = service.decrypt(encrypted);

        assertThat(decrypted).isEqualTo(plaintext);
    }

    @Test
    void decrypt_emptyPlaintext() {
        final byte[] plaintext = new byte[0];

        final byte[] encrypted = service.encrypt(plaintext);
        final byte[] decrypted = service.decrypt(encrypted);

        assertThat(decrypted).isEqualTo(plaintext);
    }

    @Test
    void decrypt_largePlaintext() {
        final byte[] plaintext = new byte[100_000];
        new SecureRandom().nextBytes(plaintext);

        final byte[] encrypted = service.encrypt(plaintext);
        final byte[] decrypted = service.decrypt(encrypted);

        assertThat(decrypted).isEqualTo(plaintext);
    }

    @Test
    void decrypt_markdownContent() {
        final String markdown = "# Heading\n\nSome **markdown** content.\n";
        final byte[] plaintext = markdown.getBytes(StandardCharsets.UTF_8);

        final byte[] encrypted = service.encrypt(plaintext);
        final String result = new String(service.decrypt(encrypted), StandardCharsets.UTF_8);

        assertThat(result).isEqualTo(markdown);
    }

    @Test
    void encryptToBase64_returnsValidBase64() {
        final byte[] plaintext = "base64 test".getBytes(StandardCharsets.UTF_8);

        final String base64 = service.encryptToBase64(plaintext);

        // must not throw
        Base64.getDecoder().decode(base64);
        assertThat(base64).isNotBlank();
    }

    @Test
    void decryptFromBase64_roundtrip() {
        final byte[] plaintext = "round trip via base64".getBytes(StandardCharsets.UTF_8);

        final String base64 = service.encryptToBase64(plaintext);
        final byte[] decrypted = service.decryptFromBase64(base64);

        assertThat(decrypted).isEqualTo(plaintext);
    }

    @Test
    void decryptFromBase64_stripsTrailingNewline() {
        final byte[] plaintext = "newline test".getBytes(StandardCharsets.UTF_8);

        final String base64WithNewline = service.encryptToBase64(plaintext) + "\n";
        final byte[] decrypted = service.decryptFromBase64(base64WithNewline);

        assertThat(decrypted).isEqualTo(plaintext);
    }

    @Test
    void decrypt_withWrongKey_throwsCryptoException() {
        final byte[] plaintext = "secret".getBytes(StandardCharsets.UTF_8);
        final byte[] encrypted = service.encrypt(plaintext);

        final byte[] wrongKey = new byte[32];
        new SecureRandom().nextBytes(wrongKey);
        final AesCryptoService wrongService = new AesCryptoService(new CryptoConfig(wrongKey));

        assertThatThrownBy(() -> wrongService.decrypt(encrypted))
            .isInstanceOf(CryptoException.class);
    }
}
