package info.setmy.jwt.models;

import static info.setmy.jwt.models.Data.SERVICE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class ContentTest extends Data {

    @BeforeEach
    public void beforeEach() {
        token = spy(new ExtendedJWTToken(SERVICE_NAME));
        doReturn(UUID_STRING).when(token).newUUIDString();
        doReturn(MOMENT_MINUS_30).when(token).getNotBefore();
        doReturn(MOMENT_MINUS_30).when(token).getIssuedAt();
        doReturn(MOMENT_MINUS_15).when(token).getExpiresAt();

        rsaToken = spy(new ExtendedJWTToken(RSA_SERVICE_NAME));
        doReturn(Data.UUID_STRING).when(rsaToken).newUUIDString();
        doReturn(MOMENT_MINUS_30).when(rsaToken).getNotBefore();
        doReturn(MOMENT_MINUS_30).when(rsaToken).getIssuedAt();
        doReturn(MOMENT_MINUS_15).when(rsaToken).getExpiresAt();
    }

    @Test
    public void tokenContent() {
        makeFullyPopulatedToken(token);
        final String tokenString = token.toString();
        // https://www.base64decode.org/
        // {"typ":"JWT","alg":"HS256"}
        // eyJzdHJpbmdDbGFpbSI6InNvbWV0aGluZyIsInN1YiI6IjMxMjEyMTIxMjEyMDAwMCIsImZubSI6IkZpcnN0TmFtZSIsImlzcyI6IkhlYXIgQW5kIFNlZSBTeXN0ZW1zIExMQyIsImxubSI6Ikxhc3ROYW1lIiwibW5tIjoiTWlkZGxlTmFtZSIsImRvdWJsZUNsYWltIjoxLjIsImludENsYWltIjoxMjMsInVpZCI6IiMxMjM0NSIsImF1ZCI6WyJIZWxsbyIsIldvcmxkIiwiYXVkaWVuY2UiXSwiZGF0ZUNsYWltIjo0MTMzNjY0OTAwLCJuYmYiOjQxMzM2NjMxMDAsImxvbmdDbGFpbSI6MzIxLCJib29sZWFuQ2xhaW0iOnRydWUsInJscyI6WyJBRE1JTklTVFJBVE9SIiwiVVNFUiJdLCJleHAiOjQxMzM2NjQwMDAsImlhdCI6NDEzMzY2MzEwMCwianRpIjoiYWZhZDgyMTItMjdkYS00NmNkLWE5MGItY2U3YzI1MDJiOTUzIn0
        assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRlQ2xhaW0iOjQxMzM2NjQ5MDAsImludENsYWltIjoxMjMsImxvbmdDbGFpbSI6MzIxLCJib29sZWFuQ2xhaW0iOnRydWUsInN0cmluZ0NsYWltIjoic29tZXRoaW5nIiwiZG91YmxlQ2xhaW0iOjEuMiwidWlkIjoiIzEyMzQ1IiwicmxzIjpbIkFETUlOSVNUUkFUT1IiLCJVU0VSIl0sImZubSI6IkZpcnN0TmFtZSIsIm1ubSI6Ik1pZGRsZU5hbWUiLCJsbm0iOiJMYXN0TmFtZSIsImlzcyI6IkhlYXIgQW5kIFNlZSBTeXN0ZW1zIExMQyIsInN1YiI6IjMxMjEyMTIxMjEyMDAwMCIsImlhdCI6NDEzMzY2MzEwMCwiYXVkIjpbIkhlbGxvIiwiV29ybGQiLCJhdWRpZW5jZSJdLCJqdGkiOiJhZmFkODIxMi0yN2RhLTQ2Y2QtYTkwYi1jZTdjMjUwMmI5NTMiLCJuYmYiOjQxMzM2NjMxMDAsImV4cCI6NDEzMzY2NDAwMH0.AuygM-aQAaakO3r4JlYa0Ip_gzjduXe0ZtU18vSAnL4", tokenString);
    }

    @Test
    public void tokenParsing() {
        final ExtendedJWTToken methodToken = new ExtendedJWTToken(SERVICE_NAME);
        makeFullyPopulatedToken(methodToken);
        final String tokenString = methodToken.toString();

        final ExtendedJWTToken newToken = new ExtendedJWTToken(SERVICE_NAME);
        newToken.parse(tokenString);
        assertThat(newToken.getSubject()).isEqualTo("312121212120000");
        assertThat(newToken.getAudience()).isEqualTo(new String[]{"Hello", "World", "audience"});
        assertThat(newToken.getUserId()).isEqualTo("#12345");
        assertThat(newToken.getRoles()).isEqualTo(new String[]{"ADMINISTRATOR", "USER"});
        assertThat(newToken.getClaimAsDate("dateClaim")).isEqualTo(MOMENT);
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
    public void rsaTokenParsing() {
        final ExtendedJWTToken methodToken = new ExtendedJWTToken(RSA_SERVICE_NAME);
        makeFullyPopulatedToken(methodToken);
        final String tokenString = methodToken.toString();

        final ExtendedJWTToken newToken = new ExtendedJWTToken(RSA_SERVICE_NAME);
        newToken.parse(tokenString);
        assertThat(newToken.getSubject()).isEqualTo("312121212120000");
        assertThat(newToken.getAudience()).isEqualTo(new String[]{"Hello", "World", "audience"});
        assertThat(newToken.getUserId()).isEqualTo("#12345");
        assertThat(newToken.getRoles()).isEqualTo(new String[]{"ADMINISTRATOR", "USER"});
        assertThat(newToken.getClaimAsDate("dateClaim")).isEqualTo(MOMENT);
        assertThat(newToken.getClaimAsInteger("intClaim")).isEqualTo((Integer) 123);
        assertThat(newToken.getClaimAsLong("longClaim")).isEqualTo((Long) 321L);
        assertTrue(newToken.getClaimAsBoolean("booleanClaim"));
        assertThat(newToken.getClaimAsString("stringClaim")).isEqualTo("something");
        assertThat(newToken.getClaimAsDouble("doubleClaim")).isEqualTo(1.2d);
        assertThat(newToken.getFirstName()).isEqualTo("FirstName");
        assertThat(newToken.getMiddleName()).isEqualTo("MiddleName");
        assertThat(newToken.getLastName()).isEqualTo("LastName");
    }

    public static void makeFullyPopulatedToken(final ExtendedJWTToken methodToken) {
        methodToken.setDefaults();
        methodToken.setUUIDJwtId();
        methodToken.setSubject("312121212120000");
        methodToken.setAudience(new String[]{"Hello", "World", "audience"});
        methodToken.setUserId("#12345");
        methodToken.setRoles(new String[]{"ADMINISTRATOR", "USER"});
        methodToken.setClaim("dateClaim", MOMENT);
        methodToken.setClaim("intClaim", (Integer) 123);
        methodToken.setClaim("longClaim", (Long) 321L);
        methodToken.setClaim("booleanClaim", true);
        methodToken.setClaim("stringClaim", "something");
        methodToken.setClaim("doubleClaim", 1.2d);
        methodToken.setFirstName("FirstName");
        methodToken.setMiddleName("MiddleName");
        methodToken.setLastName("LastName");
    }

    @Test
    public void rsaTokenContent() {
        makeFullyPopulatedToken(rsaToken);
        final String tokenString = rsaToken.toString();
        // https://www.base64decode.org/
        // {"typ":"JWT","alg":"RS256"}
        // {"stringClaim":"something","sub":"312121212120000","fnm":"FirstName","iss":"Hear And See Systems LLC","lnm":"LastName","mnm":"MiddleName","doubleClaim":1.2,"intClaim":123,"uid":"#12345","aud":["Hello","World","audience"],"dateClaim":4133664900,"nbf":4133663100,"longClaim":321,"booleanClaim":true,"rls":["ADMINISTRATOR","USER"],"exp":4133664000,"iat":4133663100,"jti":"afad8212-27da-46cd-a90b-ce7c2502b953"}
        assertEquals("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRlQ2xhaW0iOjQxMzM2NjQ5MDAsImludENsYWltIjoxMjMsImxvbmdDbGFpbSI6MzIxLCJib29sZWFuQ2xhaW0iOnRydWUsInN0cmluZ0NsYWltIjoic29tZXRoaW5nIiwiZG91YmxlQ2xhaW0iOjEuMiwidWlkIjoiIzEyMzQ1IiwicmxzIjpbIkFETUlOSVNUUkFUT1IiLCJVU0VSIl0sImZubSI6IkZpcnN0TmFtZSIsIm1ubSI6Ik1pZGRsZU5hbWUiLCJsbm0iOiJMYXN0TmFtZSIsImlzcyI6IkhlYXIgQW5kIFNlZSBTeXN0ZW1zIExMQyIsInN1YiI6IjMxMjEyMTIxMjEyMDAwMCIsImlhdCI6NDEzMzY2MzEwMCwiYXVkIjpbIkhlbGxvIiwiV29ybGQiLCJhdWRpZW5jZSJdLCJqdGkiOiJhZmFkODIxMi0yN2RhLTQ2Y2QtYTkwYi1jZTdjMjUwMmI5NTMiLCJuYmYiOjQxMzM2NjMxMDAsImV4cCI6NDEzMzY2NDAwMH0.LbMTA6BeOVg2CtV4QuQkLKxrHVQNj8fNNZQm5sU3gZ-bP702B6FPxu0jYOBfgzUEkQ3Lxww0a7NbXAE7vx88iwq7X048Jk0K7z--Me_pO69L3zAPO-dj4c1iBLhrJDkgiqBFQpyM0pA8HHEYplgOKRd_O55lu4k4h25gk-mK-RjUIKNcNufX4jMMHIlt-3I3MR3ZYmRx8qOMG9g_zpkVvM5d_4YV1U8nfUv9YbFDw6NGYW0n8lO9NnXO-1FGfDpAuiiRtk0ywZ4N5AbkjQtrDdHH_sNqi-u1TEWVUBWObg5TxkbmDJrcNVHhB_BWiekufuDgiKqu0VrB3MkhIrbyXQ", tokenString);
    }
}
