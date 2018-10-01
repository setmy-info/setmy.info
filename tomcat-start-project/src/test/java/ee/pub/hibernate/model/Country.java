package ee.pub.hibernate.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Country extends Entity {

    private World World;

    private String name;

    private List<Company> companies = new ArrayList<>();

    public Country(final String countryName) {
        this.name = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return World;
    }

    public void setWorld(World World) {
        this.World = World;
    }

    public List<Company> getCompanies() {
        return Collections.unmodifiableList(companies);
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public void add(final Company company) {
        company.setCountry(this);
        this.companies.add(company);
    }
}
