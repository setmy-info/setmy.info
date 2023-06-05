package info.setmy.jwt.models;

import info.setmy.exceptions.UncheckedException;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Header manipulation tests ({"alg":"none"} etc)
 *
 * https://auth0.com/blog/critical-vulnerabilities-in-json-web-token-libraries/
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class HeaderManipulationTest extends Data {

    @BeforeEach
    public void beforeEach() {
        token = new ExtendedJWTToken(SERVICE_NAME);
    }

    // {}
    static final String EMPTY_HEADER = "e30=";
    // {"alg":"none"}
    static final String ALG_NONE_HEADER = "eyJhbGciOiJub25lIn0=";
    // {"alg":"xyzabc"}
    static final String ALG_UNKNOWN_HEADER = "eyJhbGciOiJ4eXphYmMifQ==";
    // {"typ":"JWT","alg":"HS256"}
    static final String HEADER = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9";
    // {"iss":"Hear And See Systems LLC"}
    static final String PAYLOAD = "eyJpc3MiOiJIZWFyIEFuZCBTZWUgU3lzdGVtcyBMTEMifQ";
    static final String SIGNATURE = "5r0jwCR68Rer_Q-OOKcWmqTD0H4OzCXGV9vf5J4i-0g";

    @Test
    public void headerRemoved1() {
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            final String tokenString = "." + PAYLOAD + "." + SIGNATURE;
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.JWTDecodeException: The string '' doesn't have a valid JSON format.", exception.getMessage());
    }

    @Test
    public void headerRemoved2() {
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            final String tokenString = PAYLOAD + "." + SIGNATURE;
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.JWTDecodeException: The token was expected to have 3 parts, but got 2.", exception.getMessage());
    }

    @Test
    public void headerSetEmptyObject() {
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            final String tokenString = EMPTY_HEADER + "." + PAYLOAD + "." + SIGNATURE;
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.AlgorithmMismatchException: The provided Algorithm doesn't match the one defined in the JWT's Header.", exception.getMessage());
    }

    @Test
    public void headerModifiedToNoneAlgorithm() {
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            final String tokenString = ALG_NONE_HEADER + "." + PAYLOAD + "." + SIGNATURE;
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.AlgorithmMismatchException: The provided Algorithm doesn't match the one defined in the JWT's Header.", exception.getMessage());
    }

    @Test
    public void headerModifiedToUnknownAlgorithm() {
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            final String tokenString = ALG_UNKNOWN_HEADER + "." + PAYLOAD + "." + SIGNATURE;
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.AlgorithmMismatchException: The provided Algorithm doesn't match the one defined in the JWT's Header.", exception.getMessage());
    }
}
