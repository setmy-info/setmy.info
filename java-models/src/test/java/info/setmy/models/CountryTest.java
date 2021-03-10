package info.setmy.models;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class CountryTest {

    @Test
    public void getNumericString() {
        final Country country = new Country();
        country.setNumeric(0);
        assertThat(country.getNumericString()).isEqualTo("000");
        country.setNumeric(1);
        assertThat(country.getNumericString()).isEqualTo("001");
        country.setNumeric(10);
        assertThat(country.getNumericString()).isEqualTo("010");
        country.setNumeric(100);
        assertThat(country.getNumericString()).isEqualTo("100");
        country.setNumeric(1000);
        assertThat(country.getNumericString()).isEqualTo("1000");
    }
}
