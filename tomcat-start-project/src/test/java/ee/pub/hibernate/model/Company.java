package ee.pub.hibernate.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company extends Entity {

    private String name;

    private Country country;

    private List<SalesObject> salesObjects = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<Service> services = new ArrayList<>();

    public Company(final String countryName) {
        this.name = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<SalesObject> getSalesObjects() {
        return Collections.unmodifiableList(salesObjects);
    }

    public void setSalesObjects(List<SalesObject> salesObjects) {
        this.salesObjects = salesObjects;
    }

    public void add(SalesObject salesObjects) {
        salesObjects.setCompany(this);
        this.salesObjects.add(salesObjects);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
