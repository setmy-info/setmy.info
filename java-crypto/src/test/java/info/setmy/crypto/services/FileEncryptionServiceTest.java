package info.setmy.crypto.services;

import info.setmy.crypto.models.CryptoConfig;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
class FileEncryptionServiceTest {

    @TempDir
    File tempDir;

    private FileEncryptionService fileEncryptionService;

    @BeforeEach
    void setUp() {
        final byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        final AesCryptoService aesCryptoService = new AesCryptoService(new CryptoConfig(key));
        fileEncryptionService = new FileEncryptionService(aesCryptoService);
    }

    @Test
    void encryptFile_createsOutputFile() throws IOException {
        final File input = new File(tempDir, "plain.md");
        final File output = new File(tempDir, "encrypted.md");
        Files.writeString(input.toPath(), "# Hello World", StandardCharsets.UTF_8);

        fileEncryptionService.encryptFile(input, output);

        assertThat(output).exists();
    }

    @Test
    void encryptFile_outputIsBase64() throws IOException {
        final File input = new File(tempDir, "plain.md");
        final File output = new File(tempDir, "encrypted.md");
        Files.writeString(input.toPath(), "some content", StandardCharsets.UTF_8);

        fileEncryptionService.encryptFile(input, output);

        final String content = Files.readString(output.toPath()).strip();
        // Base64 characters only (+ / = and alphanumerics)
        assertThat(content).matches("[A-Za-z0-9+/=]+");
    }

    @Test
    void decryptFile_roundtrip() throws IOException {
        final String original = "# Profile\n\nSome **markdown** content.\n";
        final File plain = new File(tempDir, "plain.md");
        final File encrypted = new File(tempDir, "encrypted.md");
        final File decrypted = new File(tempDir, "decrypted.md");
        Files.writeString(plain.toPath(), original, StandardCharsets.UTF_8);

        fileEncryptionService.encryptFile(plain, encrypted);
        fileEncryptionService.decryptFile(encrypted, decrypted);

        final String result = Files.readString(decrypted.toPath(), StandardCharsets.UTF_8);
        assertThat(result).isEqualTo(original);
    }

    @Test
    void decryptFileToString_roundtrip() throws IOException {
        final String original = "# Java Profile\n\n- JUnit 5\n- AssertJ\n";
        final File plain = new File(tempDir, "plain.md");
        final File encrypted = new File(tempDir, "encrypted.md");
        Files.writeString(plain.toPath(), original, StandardCharsets.UTF_8);

        fileEncryptionService.encryptFile(plain, encrypted);
        final String result = fileEncryptionService.decryptFileToString(encrypted);

        assertThat(result).isEqualTo(original);
    }

    @Test
    void encryptFile_twiceProducesDifferentOutput() throws IOException {
        final File input = new File(tempDir, "plain.md");
        final File out1 = new File(tempDir, "enc1.md");
        final File out2 = new File(tempDir, "enc2.md");
        Files.writeString(input.toPath(), "same content", StandardCharsets.UTF_8);

        fileEncryptionService.encryptFile(input, out1);
        fileEncryptionService.encryptFile(input, out2);

        final String content1 = Files.readString(out1.toPath());
        final String content2 = Files.readString(out2.toPath());
        assertThat(content1).isNotEqualTo(content2);
    }

    @Test
    void encryptFile_largeFile() throws IOException {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append("Line ").append(i).append(": some content here\n");
        }
        final String original = sb.toString();
        final File plain = new File(tempDir, "large.md");
        final File encrypted = new File(tempDir, "large_enc.md");
        Files.writeString(plain.toPath(), original, StandardCharsets.UTF_8);

        fileEncryptionService.encryptFile(plain, encrypted);
        final String result = fileEncryptionService.decryptFileToString(encrypted);

        assertThat(result).isEqualTo(original);
    }
}
