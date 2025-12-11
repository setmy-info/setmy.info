package info.setmy;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator {

    private static final Calculator INSTANCE = new Calculator();

    public static Calculator getInstance() {
        return INSTANCE;
    }

    public int add(int a, int b) {
        return a + b;
    }
}
