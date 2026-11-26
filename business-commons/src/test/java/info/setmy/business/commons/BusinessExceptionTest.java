package info.setmy.business.commons;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessExceptionTest {

    @Test
    void message() {
        assertThat(new BusinessException("failed")).hasMessage("failed").hasNoCause();
    }

    @Test
    void cause() {
        final IllegalStateException cause = new IllegalStateException("cause");

        assertThat(new BusinessException("failed", cause)).hasMessage("failed").hasCause(cause);
    }
}
