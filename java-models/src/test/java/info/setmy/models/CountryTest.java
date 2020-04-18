package info.setmy.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class CountryTest {

    @Test
    public void getNumericString() {
        final Country country = new Country();
        country.setNumeric(0);
        assertThat(country.getNumericString(), is(equalTo("000")));
        country.setNumeric(1);
        assertThat(country.getNumericString(), is(equalTo("001")));
        country.setNumeric(10);
        assertThat(country.getNumericString(), is(equalTo("010")));
        country.setNumeric(100);
        assertThat(country.getNumericString(), is(equalTo("100")));
        country.setNumeric(1000);
        assertThat(country.getNumericString(), is(equalTo("1000")));
    }
}
