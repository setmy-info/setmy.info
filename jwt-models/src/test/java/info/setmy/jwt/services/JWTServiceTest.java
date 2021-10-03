package info.setmy.jwt.services;

import info.setmy.exceptions.UncheckedException;
import static info.setmy.jwt.models.ContentTest.makeFullyPopulatedToken;
import info.setmy.jwt.models.Data;
import info.setmy.jwt.models.ExtendedJWTToken;
import info.setmy.jwt.models.JWTToken;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class JWTServiceTest extends Data {

    @Test
    public void constructorShouldSetServiceName() {
        assertEquals("HS256", service.getAlgorithmName());
    }

    @Test
    public void findAlgorithm_shouldThrowExceptionWhenalgorithmNameIsNotSet() throws IllegalArgumentException, UnsupportedEncodingException {
        final JWTService testableService = new JWTService(
                SERVICE_NAME, ISSUER,
                EXAMPLE_SECRET_KEY,
                null,
                EXAMPLE_SESSION_MINUTES);
        assertThat(testableService.getAlgorithmName()).isEqualTo(null);
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            testableService.findAlgorithm();
        });
        assertEquals("Algorithm name should be set", exception.getMessage());

    }

    @Test
    public void findAlgorithm_shouldThrowExceptionWhenalgorithmNameIsEmpty() throws IllegalArgumentException, UnsupportedEncodingException {
        final JWTService testableService = new JWTService(
                SERVICE_NAME, ISSUER,
                EXAMPLE_SECRET_KEY,
                "",
                EXAMPLE_SESSION_MINUTES);
        assertThat(testableService.getAlgorithmName()).isEqualTo("");
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            testableService.findAlgorithm();
        });
        assertEquals("Algorithm \"\" is not supported!", exception.getMessage());

    }

    @Test
    public void init_shouldThrowExceptionWhenInstanceIsAlreadInializedAndThereforeRegistered() {
        final JWTService testableService = new JWTService(
                SERVICE_NAME,
                ISSUER,
                EXAMPLE_SECRET_KEY,
                ALGORITHM_NAME,
                EXAMPLE_SESSION_MINUTES);
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            testableService.init();
        });
        assertEquals("Instance of service by name \"JWT_FOR_FRONTENDS_SERVICE\" exists!", exception.getMessage());
    }

    @Test
    public void cleanPublicKeyParts_shouldEliminate() {
        final String stringToClean
                = "-----BEGIN PUBLIC KEY-----\n"
                + "a\n"
                + "b\n"
                + "c"
                + "d"
                + "-----END PUBLIC KEY-----";
        assertEquals("abcd", service.cleanPublicKeyParts(stringToClean));
    }

    @Test
    public void decodePEMToBytes_shouldEliminate() {
        final String abcd = "YWJjZA==";
        assertThat(service.decodePEMToBytes(abcd)).isEqualTo("abcd".getBytes(Charset.forName("UTF-8")));
    }

    @Test
    public void readPublicKey_shouldParsePublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
        final RSAPublicKey publicKey = rsaService.readRSAPublicKey();
        final BigInteger modulus = publicKey.getModulus();
        final BigInteger publicExponent = publicKey.getPublicExponent();
        assertThat(publicKey.getAlgorithm()).isEqualTo("RSA");
        assertThat(publicKey.getFormat()).isEqualTo("X.509");
        //System.out.println(modulus.toString(;
        assertThat(modulus.toString()).isEqualTo("27696097220353534803647965017903902497175907807670235718915818713239864153813796923500397161811901156693236273144534415023920860225144403553579565098261469648817825936479151083183610308446328052285021731969302929263110682483078004743892329632730445819513465580920567685381625173380037654325274984953429375850563810278262988877781572440476686009700919739381602960675523077935820173578523406785764806005955343600862707108055266121415592537261992503697902475487587567075128303245364730517425032441297269572590188321639397800883009275007834338974204295014699077185779286916518123125085550461589761993017326274659482970273");
        assertThat(publicExponent.toString()).isEqualTo("65537");
    }

    @Test
    public void toRSAPrivateKey_shouldParsePrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
        final RSAPrivateKey privateKey = rsaService.readRSAPrivateKey();
        final BigInteger modulus = privateKey.getModulus();
        final BigInteger privateExponent = privateKey.getPrivateExponent();

        assertThat(privateKey.getAlgorithm()).isEqualTo("RSA");
        assertThat(privateKey.getFormat()).isEqualTo("PKCS#8");
        //System.out.println(modulus.toString(;
        assertThat(modulus.toString()).isEqualTo("27696097220353534803647965017903902497175907807670235718915818713239864153813796923500397161811901156693236273144534415023920860225144403553579565098261469648817825936479151083183610308446328052285021731969302929263110682483078004743892329632730445819513465580920567685381625173380037654325274984953429375850563810278262988877781572440476686009700919739381602960675523077935820173578523406785764806005955343600862707108055266121415592537261992503697902475487587567075128303245364730517425032441297269572590188321639397800883009275007834338974204295014699077185779286916518123125085550461589761993017326274659482970273");
        assertThat(privateExponent.toString()).isEqualTo("22026462658071724178722486300840278042255603565063709902888618446873902674228739634859151326285885838350979870799186387009807881895642941508096502929436593978455435946644335773785998029914873497614288382012787249586074917706036417371201185784938943902816139578301736550799940272849549759389164250587571791487097175957323684878002122583408642835907954199711044358855571213849505244430713277456473869642866525444891590789423743938913597790278653662322694183984053642090508987859276089600071804159728399829045231889742880031687572425297484480942191243986023646418300336479974868357737744451643519246395004412033346216677");
    }

    @Test
    public void constructorShouldSetServiceNameForRSAService() {
        assertEquals("RS256", rsaService.getAlgorithmName());
    }

    @Test
    public void shouldInitWithoutErrorsAlgiorithmHS384() {
        final String algorithmName = "HS384";
        final String newServiceName = SERVICE_NAME + algorithmName;
        final JWTService testableService = new JWTService(
                newServiceName,
                ISSUER,
                EXAMPLE_SECRET_KEY,
                algorithmName,
                EXAMPLE_SESSION_MINUTES);
        testableService.init();
        final JWTToken methodToken = new ExtendedJWTToken(newServiceName);
        makeFullyPopulatedToken((ExtendedJWTToken) methodToken);
        final String tokenString = methodToken.toString();
        assertEquals(0, tokenString.indexOf("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9"));//{"typ":"JWT","alg":"HS384"}

        final ExtendedJWTToken newToken = new ExtendedJWTToken(newServiceName);
        newToken.parse(tokenString);
        assertThat(newToken.getSubject()).isEqualTo("312121212120000");
        assertThat(newToken.getAudience()).isEqualTo(new String[]{"Hello", "World", "audience"});
        assertThat(newToken.getUserId()).isEqualTo("#12345");
        assertThat(newToken.getRoles()).isEqualTo(new String[]{"ADMINISTRATOR", "USER"});
        assertThat(newToken.getClaimAsInteger("intClaim")).isEqualTo((Integer) 123);
        assertThat(newToken.getClaimAsLong("longClaim")).isEqualTo((Long) 321L);
        assertTrue(newToken.getClaimAsBoolean("booleanClaim"));
        assertThat(newToken.getClaimAsString("stringClaim")).isEqualTo("something");
        assertThat(newToken.getClaimAsDouble("doubleClaim")).isEqualTo(1.2d);
        assertThat(newToken.getFirstName()).isEqualTo("FirstName");
        assertThat(newToken.getMiddleName()).isEqualTo("MiddleName");
        assertThat(newToken.getLastName()).isEqualTo("LastName");
    }

    @Test
    public void shouldInitWithoutErrorsAlgiorithmHS512() {
        final String algorithmName = "HS512";
        final String newServiceName = SERVICE_NAME + algorithmName;
        final JWTService testableService = new JWTService(
                newServiceName,
                ISSUER,
                EXAMPLE_SECRET_KEY,
                algorithmName,
                EXAMPLE_SESSION_MINUTES);
        testableService.init();
        final JWTToken methodToken = new ExtendedJWTToken(newServiceName);
        makeFullyPopulatedToken((ExtendedJWTToken) methodToken);
        final String tokenString = methodToken.toString();
        assertEquals(0, tokenString.indexOf("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9"));//{"typ":"JWT","alg":"HS512"}

        final ExtendedJWTToken newToken = new ExtendedJWTToken(newServiceName);
        newToken.parse(tokenString);
        assertThat(newToken.getSubject()).isEqualTo("312121212120000");
        assertThat(newToken.getAudience()).isEqualTo(new String[]{"Hello", "World", "audience"});
        assertThat(newToken.getUserId()).isEqualTo("#12345");
        assertThat(newToken.getRoles()).isEqualTo(new String[]{"ADMINISTRATOR", "USER"});
        assertThat(newToken.getClaimAsInteger("intClaim")).isEqualTo((Integer) 123);
        assertThat(newToken.getClaimAsLong("longClaim")).isEqualTo((Long) 321L);
        assertTrue(newToken.getClaimAsBoolean("booleanClaim"));
        assertThat(newToken.getClaimAsString("stringClaim")).isEqualTo("something");
        assertThat(newToken.getClaimAsDouble("doubleClaim")).isEqualTo(1.2d);
        assertThat(newToken.getFirstName()).isEqualTo("FirstName");
        assertThat(newToken.getMiddleName()).isEqualTo("MiddleName");
        assertThat(newToken.getLastName()).isEqualTo("LastName");
    }
}
