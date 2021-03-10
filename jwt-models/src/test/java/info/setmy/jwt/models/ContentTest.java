package info.setmy.jwt.models;

import static info.setmy.jwt.models.Data.SERVICE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
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
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdHJpbmdDbGFpbSI6InNvbWV0aGluZyIsInN1YiI6IjMxMjEyMTIxMjEyMDAwMCIsImZubSI6IkZpcnN0TmFtZSIsImlzcyI6IkhlYXIgQW5kIFNlZSBTeXN0ZW1zIExMQyIsImxubSI6Ikxhc3ROYW1lIiwibW5tIjoiTWlkZGxlTmFtZSIsImRvdWJsZUNsYWltIjoxLjIsImludENsYWltIjoxMjMsInVpZCI6IiMxMjM0NSIsImF1ZCI6WyJIZWxsbyIsIldvcmxkIiwiYXVkaWVuY2UiXSwiZGF0ZUNsYWltIjo0MTMzNjY0OTAwLCJuYmYiOjQxMzM2NjMxMDAsImxvbmdDbGFpbSI6MzIxLCJib29sZWFuQ2xhaW0iOnRydWUsInJscyI6WyJBRE1JTklTVFJBVE9SIiwiVVNFUiJdLCJleHAiOjQxMzM2NjQwMDAsImlhdCI6NDEzMzY2MzEwMCwianRpIjoiYWZhZDgyMTItMjdkYS00NmNkLWE5MGItY2U3YzI1MDJiOTUzIn0.GtSVRQWItjfjOqxPfKcracDnWIPeFMfS_aFDGGvFsH0", tokenString);
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
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdHJpbmdDbGFpbSI6InNvbWV0aGluZyIsInN1YiI6IjMxMjEyMTIxMjEyMDAwMCIsImZubSI6IkZpcnN0TmFtZSIsImlzcyI6IkhlYXIgQW5kIFNlZSBTeXN0ZW1zIExMQyIsImxubSI6Ikxhc3ROYW1lIiwibW5tIjoiTWlkZGxlTmFtZSIsImRvdWJsZUNsYWltIjoxLjIsImludENsYWltIjoxMjMsInVpZCI6IiMxMjM0NSIsImF1ZCI6WyJIZWxsbyIsIldvcmxkIiwiYXVkaWVuY2UiXSwiZGF0ZUNsYWltIjo0MTMzNjY0OTAwLCJuYmYiOjQxMzM2NjMxMDAsImxvbmdDbGFpbSI6MzIxLCJib29sZWFuQ2xhaW0iOnRydWUsInJscyI6WyJBRE1JTklTVFJBVE9SIiwiVVNFUiJdLCJleHAiOjQxMzM2NjQwMDAsImlhdCI6NDEzMzY2MzEwMCwianRpIjoiYWZhZDgyMTItMjdkYS00NmNkLWE5MGItY2U3YzI1MDJiOTUzIn0.I74FfdSx7_-EeAzXf6d37s9pSr5xIx_E6QlRCZsB5plv02PyAznPrvX0Q9P0m86S4QtN3gxJlTe2qqoVXkd6Bas3tWlEkYytTlVpGgrRyRTaeicFxWBI6SkcWR57GT-ivBUm0brvqsAaFG3km1TmVcY_W5qyqKhZ4Z2h1VGL6RyUWgo-HHIQ9otZN7bKWL7WRoXRIqoPf6hNIrCOFvu7bSSdqaGA5wsdf4UcbgoZvXUTZ-DrmReE54kIRq1cm8NgIPP__BpQhrnQSSFnPrWP4YKHDvRNbU2K1KGY6T8ZqBRb_Je95i0Rp65s3MqYCcQ9neqfq4aO3piYED5HKz9mtA", tokenString);
    }
}
