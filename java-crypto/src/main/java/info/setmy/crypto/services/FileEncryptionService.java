package info.setmy.crypto.services;

import info.setmy.crypto.exceptions.CryptoException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Encrypts and decrypts files using AesCryptoService.
 *
 * Encrypted files are stored as Base64 text (one line).
 * Plaintext files are read and written as raw bytes.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Slf4j
@RequiredArgsConstructor
public class FileEncryptionService {

    private final AesCryptoService aesCryptoService;

    /**
     * Read plaintext from inputFile, encrypt, write Base64 ciphertext to outputFile.
     *
     * @param inputFile  source file with plaintext content
     * @param outputFile destination file for Base64-encoded encrypted content
     */
    public void encryptFile(final File inputFile, final File outputFile) {
        log.debug("Encrypting: {} -> {}", inputFile, outputFile);
        try {
            final byte[] plaintext = Files.readAllBytes(inputFile.toPath());
            final String base64 = aesCryptoService.encryptToBase64(plaintext);
            Files.writeString(outputFile.toPath(), base64 + "\n", StandardCharsets.US_ASCII);
            log.info("Encrypted: {} -> {}", inputFile, outputFile);
        } catch (IOException ex) {
            throw new CryptoException(
                String.format("Failed to encrypt file %s -> %s", inputFile, outputFile), ex
            );
        }
    }

    /**
     * Read Base64 ciphertext from inputFile, decrypt, write plaintext bytes to outputFile.
     *
     * @param inputFile  source file with Base64-encoded encrypted content
     * @param outputFile destination file for decrypted plaintext
     */
    public void decryptFile(final File inputFile, final File outputFile) {
        log.debug("Decrypting: {} -> {}", inputFile, outputFile);
        try {
            final String base64 = Files.readString(inputFile.toPath(), StandardCharsets.US_ASCII);
            final byte[] plaintext = aesCryptoService.decryptFromBase64(base64);
            Files.write(outputFile.toPath(), plaintext);
            log.info("Decrypted: {} -> {}", inputFile, outputFile);
        } catch (IOException ex) {
            throw new CryptoException(
                String.format("Failed to decrypt file %s -> %s", inputFile, outputFile), ex
            );
        }
    }

    /**
     * Read Base64 ciphertext from inputFile, decrypt, return as UTF-8 string.
     *
     * @param inputFile source file with Base64-encoded encrypted content
     * @return decrypted content as UTF-8 string
     */
    public String decryptFileToString(final File inputFile) {
        log.debug("Decrypting to string: {}", inputFile);
        try {
            final String base64 = Files.readString(inputFile.toPath(), StandardCharsets.US_ASCII);
            final byte[] plaintext = aesCryptoService.decryptFromBase64(base64);
            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new CryptoException(
                String.format("Failed to decrypt file %s", inputFile), ex
            );
        }
    }
}
