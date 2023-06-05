package info.setmy.jwt.models;

import info.setmy.exceptions.ForbiddenException;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ContentManipulationTest extends Data {

    @BeforeEach
    public void beforeEach() {
        token = new ExtendedJWTToken(SERVICE_NAME);
    }

    // {"typ":"JWT","alg":"HS256"}
    static final String HEADER = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9";
    // http://www.onlineconversion.com/unix_time.htm
    // Expire date changed to: 4102444800 (01.01.2100 00:00:00)
    // {"stringClaim":"something","sub":"subject","iss":"Hear And See Systems LLC","doubleClaim":1.2,"ssn":"312121212120000","intClaim":123,"uid":"#12345","aud":["Hello","World","audience"],"dateClaim":1545984900,"nbf":1545983100,"longClaim":123,"booleanClaim":true,"rls":["ADMINISTRATOR","USER"],"exp":4102444800,"iat":1545983100,"jti":"afad8212-27da-46cd-a90b-ce7c2502b953"}
    static final String PAYLOAD_EXPIRE_DATECHANGED = "eyJzdHJpbmdDbGFpbSI6InNvbWV0aGluZyIsInN1YiI6InN1YmplY3QiLCJpc3MiOiJIZWFyIEFuZCBTZWUgU3lzdGVtcyBMTEMiLCJkb3VibGVDbGFpbSI6MS4yLCJzc24iOiIzMTIxMjEyMTIxMjAwMDAiLCJpbnRDbGFpbSI6MTIzLCJ1aWQiOiIjMTIzNDUiLCJhdWQiOlsiSGVsbG8iLCJXb3JsZCIsImF1ZGllbmNlIl0sImRhdGVDbGFpbSI6MTU0NTk4NDkwMCwibmJmIjoxNTQ1OTgzMTAwLCJsb25nQ2xhaW0iOjEyMywiYm9vbGVhbkNsYWltIjp0cnVlLCJybHMiOlsiQURNSU5JU1RSQVRPUiIsIlVTRVIiXSwiZXhwIjo0MTAyNDQ0ODAwLCJpYXQiOjE1NDU5ODMxMDAsImp0aSI6ImFmYWQ4MjEyLTI3ZGEtNDZjZC1hOTBiLWNlN2MyNTAyYjk1MyJ9";
    // {"stringClaim":"something","sub":"subject","iss":"Hear And See Systems LLC","doubleClaim":1.2,"ssn":"312121212120000","intClaim":123,"uid":"#54321","aud":["Hello","World","audience"],"dateClaim":1545984900,"nbf":1545983100,"longClaim":123,"booleanClaim":true,"rls":["ADMINISTRATOR","USER"],"exp":1545984000,"iat":1545983100,"jti":"afad8212-27da-46cd-a90b-ce7c2502b953"}
    static final String PAYLOAD_USER_ID_UID_CHANGED = "eyJzdHJpbmdDbGFpbSI6InNvbWV0aGluZyIsInN1YiI6InN1YmplY3QiLCJpc3MiOiJIZWFyIEFuZCBTZWUgU3lzdGVtcyBMTEMiLCJkb3VibGVDbGFpbSI6MS4yLCJzc24iOiIzMTIxMjEyMTIxMjAwMDAiLCJpbnRDbGFpbSI6MTIzLCJ1aWQiOiIjNTQzMjEiLCJhdWQiOlsiSGVsbG8iLCJXb3JsZCIsImF1ZGllbmNlIl0sImRhdGVDbGFpbSI6MTU0NTk4NDkwMCwibmJmIjoxNTQ1OTgzMTAwLCJsb25nQ2xhaW0iOjEyMywiYm9vbGVhbkNsYWltIjp0cnVlLCJybHMiOlsiQURNSU5JU1RSQVRPUiIsIlVTRVIiXSwiZXhwIjoxNTQ1OTg0MDAwLCJpYXQiOjE1NDU5ODMxMDAsImp0aSI6ImFmYWQ4MjEyLTI3ZGEtNDZjZC1hOTBiLWNlN2MyNTAyYjk1MyJ9";
    static final String SIGNATURE = "icN1383zb3hmYUfWgG3GxRu-_0y2rmoL6duQSuhpT7A";

    @Test
    public void expirationDateChanged() {
        Throwable exception = Assertions.assertThrows(ForbiddenException.class, () -> {
            final String tokenString = HEADER + "." + PAYLOAD_EXPIRE_DATECHANGED + "." + SIGNATURE;
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.SignatureVerificationException: The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256", exception.getMessage());
    }

    @Test
    public void uidChanged() {
        Throwable exception = Assertions.assertThrows(ForbiddenException.class, () -> {
            final String tokenString = HEADER + "." + PAYLOAD_USER_ID_UID_CHANGED + "." + SIGNATURE;
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.SignatureVerificationException: The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256", exception.getMessage());
    }

    /*
    @Test
    public void tokenContent() {
        // {"typ":"JWT","alg":"HS256"}
        // {"stringClaim":"something","sub":"subject","iss":"Hear And See Systems LLC","doubleClaim":1.2,"ssn":"312121212120000","intClaim":123,"uid":"#12345","aud":["Hello","World","audience"],"dateClaim":1545984900,"nbf":1545983100,"longClaim":123,"booleanClaim":true,"rls":["ADMINISTRATOR","USER"],"exp":1545984000,"iat":1545983100,"jti":"afad8212-27da-46cd-a90b-ce7c2502b953"}
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdHJpbmdDbGFpbSI6InNvbWV0aGluZyIsInN1YiI6InN1YmplY3QiLCJpc3MiOiJIZWFyIEFuZCBTZWUgU3lzdGVtcyBMTEMiLCJkb3VibGVDbGFpbSI6MS4yLCJzc24iOiIzMTIxMjEyMTIxMjAwMDAiLCJpbnRDbGFpbSI6MTIzLCJ1aWQiOiIjMTIzNDUiLCJhdWQiOlsiSGVsbG8iLCJXb3JsZCIsImF1ZGllbmNlIl0sImRhdGVDbGFpbSI6MTU0NTk4NDkwMCwibmJmIjoxNTQ1OTgzMTAwLCJsb25nQ2xhaW0iOjEyMywiYm9vbGVhbkNsYWltIjp0cnVlLCJybHMiOlsiQURNSU5JU1RSQVRPUiIsIlVTRVIiXSwiZXhwIjoxNTQ1OTg0MDAwLCJpYXQiOjE1NDU5ODMxMDAsImp0aSI6ImFmYWQ4MjEyLTI3ZGEtNDZjZC1hOTBiLWNlN2MyNTAyYjk1MyJ9.icN1383zb3hmYUfWgG3GxRu-_0y2rmoL6duQSuhpT7A", tokenString);
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            final String CREATED_DEFAULTS_EXPIRED = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1MjA0Mjc2MTksImlzcyI6IkhlYXIgQW5kIFNlZSBTeXN0ZW1zIExMQyIsImV4cCI6MTUyMDQyODUxOSwiaWF0IjoxNTIwNDI3NjE5LCJqdGkiOiI2NWZkYjRjNS05MWQwLTRkNDktOGQzZC1hNGY3OGNiOTkzYTkifQ.n_gGkkovkq1LttmGKAhMu88oz9pJOhBt3Kd7o4Oy7Fo";
            token.verify(CREATED_DEFAULTS_EXPIRED);
        });
        assertEquals("com.auth0.jwt.exceptions.TokenExpiredException: The Token has expired on Wed Mar 07 15:15:19 EET 2018.", exception.getMessage());
    }*/
}
