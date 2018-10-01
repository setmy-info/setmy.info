package ee.pub.hibernate.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/
 */
public class World extends Entity {

    private List<Country> countries = new ArrayList<>();

    public List<Country> getCountries() {
        return Collections.unmodifiableList(countries);
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void add(final Country country) {
        country.setWorld(this);
        this.countries.add(country);
    }
}
