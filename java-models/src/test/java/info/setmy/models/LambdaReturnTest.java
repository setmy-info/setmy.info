package info.setmy.models;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaReturnTest {

    @Test
    public void returningFromLambda() {
        final LambdaReturn<String> lambdaReturn = new LambdaReturn<>();
        ((Consumer<String>) string -> {
            lambdaReturn.setValue(string + "-suffix");
        }).accept("prefix");
        assertThat(lambdaReturn.getValue()).isPresent().get().isEqualTo("prefix-suffix");
    }
}
