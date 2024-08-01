package info.setmy.jwt.models;

import info.setmy.exceptions.ExpiredException;
import info.setmy.exceptions.ForbiddenException;
import static info.setmy.jwt.models.Data.nowMinus10;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class ExpirationsTest extends Data {

    @BeforeEach
    public void beforeEach() {
        java.util.TimeZone.setDefault(TimeZone.getTimeZone("Europe/Tallinn"));
        token = new ExtendedJWTToken(SERVICE_NAME);
    }

    @Test
    public void expiredTokenCases1() {
        token.setExpiresAt(nowMinus10);
        final String tokenString = token.toString();
        Throwable exception = Assertions.assertThrows(ExpiredException.class, () -> {
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.TokenExpiredException: The Token has expired on " + s(nowMinus10) + ".", exception.getMessage());
    }

    @Test
    public void expiredTokenCases2() {
        token.setIssuedAt(nowMinus20);
        token.setNotBefore(nowMinus20);
        token.setExpiresAt(nowMinus10);
        final String tokenString = token.toString();
        Throwable exception = Assertions.assertThrows(ExpiredException.class, () -> {
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.TokenExpiredException: The Token has expired on " + s(nowMinus10) + ".", exception.getMessage());
    }

    @Test
    public void inFutureToken1() {
        token.setIssuedAt(nowPlus10);
        token.setExpiresAt(nowPlus20);
        final String tokenString = token.toString();
        Throwable exception = Assertions.assertThrows(ForbiddenException.class, () -> {
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.IncorrectClaimException: The Token can't be used before " + s(nowPlus10) + ".", exception.getMessage());
    }

    @Test
    public void inFutureToken2() {
        token.setNotBefore(nowPlus10);
        token.setExpiresAt(nowPlus20);
        final String tokenString = token.toString();
        Throwable exception = Assertions.assertThrows(ForbiddenException.class, () -> {
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.IncorrectClaimException: The Token can't be used before " + s(nowPlus10) + ".", exception.getMessage());
    }

    @Test
    public void inFutureToken3() {
        token.setIssuedAt(nowPlus10);
        token.setNotBefore(nowPlus10);
        token.setExpiresAt(nowPlus20);
        final String tokenString = token.toString();
        Throwable exception = Assertions.assertThrows(ForbiddenException.class, () -> {
            token.parse(tokenString);
        });
        assertEquals("com.auth0.jwt.exceptions.IncorrectClaimException: The Token can't be used before " + s(nowPlus10) + ".", exception.getMessage());
    }

    @Test
    public void activeToken1() {
        token.setExpiresAt(nowPlus10);
        final String tokenString = token.toString();
        token.parse(tokenString);
    }

    @Test
    public void activeToken2() {
        token.setIssuedAt(NOW);
        token.setNotBefore(NOW);
        token.setExpiresAt(nowPlus10);
        final String tokenString = token.toString();
        token.parse(tokenString);
    }

    @Test
    public void activeToken3() {
        token.setDefaults();
        final String tokenString = token.toString();
        token.parse(tokenString);
    }

    @Test
    public void expiredTokenCases3() {
        Throwable exception = Assertions.assertThrows(ExpiredException.class, () -> {
            final String CREATED_DEFAULTS_EXPIRED = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1MjA0Mjc2MTksImlzcyI6IkhlYXIgQW5kIFNlZSBTeXN0ZW1zIExMQyIsImV4cCI6MTUyMDQyODUxOSwiaWF0IjoxNTIwNDI3NjE5LCJqdGkiOiI2NWZkYjRjNS05MWQwLTRkNDktOGQzZC1hNGY3OGNiOTkzYTkifQ.n_gGkkovkq1LttmGKAhMu88oz9pJOhBt3Kd7o4Oy7Fo";
            token.parse(CREATED_DEFAULTS_EXPIRED);
        });
        assertEquals("com.auth0.jwt.exceptions.TokenExpiredException: The Token has expired on 2018-03-07T13:15:19Z.", exception.getMessage());
    }

    private String s(final Date date) {
        final String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        simpleDateFormat.setTimeZone(utc);
        return simpleDateFormat.format(date);
    }
}
