package info.setmy.linguistics.models.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenTypeTest {

    Token tokenA;
    Token tokenB;
    Token tokenASecond;
    Token tokenBSecond;
    Token tokenNull;
    Token tokenEmpty;

    @BeforeEach
    public void before() {
        tokenA = new ExampleToken("a");
        tokenB = new ExampleToken("b");
        tokenASecond = new ExampleToken("a");
        tokenBSecond = new ExampleToken("b");
        tokenNull = new ExampleToken(null);
        tokenEmpty = new ExampleToken("");
    }

    @Test
    public void tokenEquals() {
        assertThat(tokenA).isEqualTo("a");
        assertThat(tokenA).isEqualTo(tokenA);
        assertThat(tokenA).isEqualTo(tokenASecond);
        assertThat(tokenA).isEqualTo(new ExampleToken('a'));

        assertThat(tokenB).isEqualTo("b");
        assertThat(tokenB).isEqualTo(tokenB);
        assertThat(tokenB).isEqualTo(tokenBSecond);
        assertThat(tokenB).isEqualTo(new ExampleToken('b'));

        assertThat(tokenA).isNotEqualTo("b");
        assertThat(tokenA).isNotEqualTo(tokenB);
        assertThat(tokenA).isNotEqualTo(tokenBSecond);
        assertThat(tokenA).isNotEqualTo(tokenNull);
        assertThat(tokenA).isNotEqualTo(tokenEmpty);
        assertThat(tokenA).isNotEqualTo(null);
        assertThat(tokenA).isNotEqualTo("");
    }

    @Test
    public void tokenHashCode() {
        assertThat(tokenA.hashCode()).isEqualTo(128);
    }

    @Test
    public void tokenToString() {
        assertThat(tokenA.toString()).isEqualTo("a");
    }

    @Test
    public void nullTokenHashCode() {
        assertThat(tokenNull.hashCode()).isEqualTo(31);
    }

    @Test
    public void nullTokenToString() {
        assertThat(tokenNull.toString()).isEqualTo("");
    }

    @Test
    public void emptyTokenHashCode() {
        assertThat(tokenEmpty.hashCode()).isEqualTo(31);
    }

    @Test
    public void emptyTokenToString() {
        assertThat(tokenEmpty.toString()).isEqualTo("");
    }
}
