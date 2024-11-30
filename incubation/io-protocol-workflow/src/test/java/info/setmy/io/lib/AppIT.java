package info.setmy.io.lib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppIT {

    App app;

    @BeforeEach
    public void before() {
        app = new App();
    }

    @Test
    public void doMain() {
        final String[] params = {"2", "3"};
        app.doMain(params);
        assertThat(app.getResult()).isEqualTo(5);
    }
}
