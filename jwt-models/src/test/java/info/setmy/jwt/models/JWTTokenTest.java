package info.setmy.jwt.models;

import info.setmy.exceptions.UncheckedException;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JWTTokenTest extends Data {

    JWTToken jwtToken;

    @BeforeEach
    public void beforeEach() {
        jwtToken = new JWTToken(SERVICE_NAME);
    }

    @Test
    public void constructorShouldSetServiceName() {
        assertEquals("JWT_FOR_FRONTENDS_SERVICE", jwtToken.getServiceName());
    }

    @Test
    public void validateForParsing_shouldThrowException() {
        Throwable exception = Assertions.assertThrows(UncheckedException.class, () -> {
            jwtToken.validateForParsing();
        });
        assertEquals("Token is not parsed!", exception.getMessage());
    }
}
