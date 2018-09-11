package info.setmy.jwt.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import info.setmy.exceptions.UncheckedException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JWTService {

    private static final Map<String, JWTService> SERVICES_MAP = new ConcurrentHashMap<>();

    private final String serviceName;

    private final String issuer;

    private final String key;

    private final Integer expirationMinutes;

    private final String algorithmName;

    private final String publicKeyFileName;

    private final String privateKeyFileName;

    private Algorithm algorithm;

    private RSAPublicKey rsaPublicKey;

    private RSAPrivateKey rsaPrivateKey;

    private ECPublicKey ecdsaSAPublicKey;

    private ECPrivateKey ecdsaPrivateKey;

    public JWTService(
            final String serviceName,
            final String issuer,
            final String key,
            final String algorithmName,
            final Integer expirationMinutes
    ) {
        this.serviceName = serviceName;
        this.issuer = issuer;
        this.key = key;
        this.expirationMinutes = expirationMinutes;
        this.algorithmName = algorithmName;
        this.publicKeyFileName = null;
        this.privateKeyFileName = null;
    }

    public JWTService(
            final String serviceName,
            final String issuer,
            final String privateKeyFileName,
            final String publicKeyFileName,
            final String algorithmName,
            final Integer expirationMinutes
    ) {
        this.serviceName = serviceName;
        this.issuer = issuer;
        this.key = null;
        this.expirationMinutes = expirationMinutes;
        this.algorithmName = algorithmName;
        this.privateKeyFileName = privateKeyFileName;
        this.publicKeyFileName = publicKeyFileName;
    }

    public void init() {
        final JWTService instance = SERVICES_MAP.get(getServiceName());
        if (instance != null) {
            throw new UncheckedException(String.format("Instance of service by name \"%s\" exists!", getServiceName()));
        }
        try {
            algorithm = findAlgorithm();
        } catch (IllegalArgumentException ex) {
            throw new UncheckedException(String.format("Illegal arguments for \"%s\" algorithm key", getAlgorithmName()), ex);
        } catch (UnsupportedEncodingException ex) {
            throw new UncheckedException("Un supported encoding", ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new UncheckedException("Algorithm is not supported", ex);
        } catch (NoSuchPaddingException ex) {
            throw new UncheckedException("Padding is not supported", ex);
        } catch (InvalidKeySpecException ex) {
            throw new UncheckedException("Invalid key spec", ex);
        } catch (InvalidKeyException ex) {
            throw new UncheckedException("Invalid key", ex);
        }
        SERVICES_MAP.put(getServiceName(), this);
    }

    Algorithm findAlgorithm() throws IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException {
        if (algorithmName == null) {
            throw new UncheckedException("Algorithm name should be set");
        }
        switch (algorithmName) {
            case "HS256":
                return Algorithm.HMAC256(this.getKey());
            case "HS384":
                return Algorithm.HMAC384(this.getKey());
            case "HS512":
                return Algorithm.HMAC512(this.getKey());
            case "RS256":
                rsaKeysReading();
                return Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            case "RSA384":
                rsaKeysReading();
                return Algorithm.RSA384(rsaPublicKey, rsaPrivateKey);
            case "RSA512":
                rsaKeysReading();
                return Algorithm.RSA512(rsaPublicKey, rsaPrivateKey);
            case "ES256":
                ecdsaKeysReading();
                return Algorithm.ECDSA256(ecdsaSAPublicKey, ecdsaPrivateKey);
            case "ES384":
                ecdsaKeysReading();
                return Algorithm.ECDSA384(ecdsaSAPublicKey, ecdsaPrivateKey);
            case "ES512":
                ecdsaKeysReading();
                return Algorithm.ECDSA512(ecdsaSAPublicKey, ecdsaPrivateKey);
            default:
        }
        throw new UncheckedException(String.format("Algorithm \"%s\" is not supported!", algorithmName));
    }

    private void rsaKeysReading() throws InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, NoSuchAlgorithmException {
        this.rsaPublicKey = readRSAPublicKey();
        this.rsaPrivateKey = readRSAPrivateKey();
    }

    void ecdsaKeysReading() {
        this.ecdsaSAPublicKey = readECDSAPublicKey();
        this.ecdsaPrivateKey = readECDSAPrivateKey();
    }

    RSAPublicKey readRSAPublicKey() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException {
        try {
            final String publicKey = IOUtils.toString(new FileInputStream(publicKeyFileName), Charset.forName("UTF-8"));
            final String cleanedPublicKey = cleanPublicKeyParts(publicKey);
            final byte[] data = decodePEMToBytes(cleanedPublicKey);
            final Cipher cipher = Cipher.getInstance("RSA");
            final RSAPublicKey resultKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(data));
            cipher.init(Cipher.ENCRYPT_MODE, resultKey);
            return resultKey;
        } catch (FileNotFoundException ex) {
            throw new UncheckedException(String.format("Can't read keyfle \"%s\".", publicKeyFileName), ex);
        } catch (IOException ex) {
            throw new UncheckedException(String.format("Can't read keyfle \"%s\".", publicKeyFileName), ex);
        }
    }

    RSAPrivateKey readRSAPrivateKey() {
        try {
            final PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(IOUtils.toByteArray(new FileInputStream(privateKeyFileName)));
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException ex) {
            throw new UncheckedException(String.format("Can't read keyfle \"%s\".", privateKeyFileName), ex);
        }
    }

    ECPublicKey readECDSAPublicKey() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    ECPrivateKey readECDSAPrivateKey() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    String cleanPublicKeyParts(final String pemDataString) {
        final String cleanData = pemDataString
                .replace("-----BEGIN PUBLIC KEY-----\n", "")
                .replace("\n", "")
                .replace("-----END PUBLIC KEY-----", "");
        return cleanData;
    }

    byte[] decodePEMToBytes(final String cleanedKey) {
        final byte[] bytes = Base64.getDecoder().decode(cleanedKey);
        return bytes;
    }

    public static JWTService instance(final String serviceName) {
        return SERVICES_MAP.get(serviceName);
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public String getIssuer() {
        return issuer;
    }

    public JWTCreator.Builder create() {
        return JWT.create();
    }

    public String getKey() {
        return key;
    }

    public Integer getExpirationMinutes() {
        return expirationMinutes;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
