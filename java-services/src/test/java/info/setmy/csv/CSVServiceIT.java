package info.setmy.csv;

import org.junit.jupiter.api.BeforeEach;

public class CSVServiceIT {

    private CSVService service;
    private SCVConfig config;

    @BeforeEach
    public void setup() {
        config = new SCVConfig();
        service = new CSVService(config);
    }
}
