package ee.pub.hibernate.service;

import ee.pub.hibernate.model.Company;
import ee.pub.hibernate.model.Country;
import ee.pub.hibernate.model.Product;
import ee.pub.hibernate.model.SalesObject;
import ee.pub.hibernate.model.Service;
import ee.pub.hibernate.model.World;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * mvn clean validate -Dit.test=ee.pub.hibernate.service.WorldServiceIT
 */
@ContextConfiguration("classpath:hibernate/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class WorldServiceIT {

    @Autowired
    WorldService worldService;

    @Test
    public void testing() {
        World world = new World();
        Country estonia = new Country("Estonia");
        Country finland = new Country("Finland");
        world.add(estonia);
        world.add(finland);
        Company estonianCompany = new Company("Autorollo OÜ");
        Company finnishCompany = new Company("NOKIA");
        estonia.add(estonianCompany);
        finland.add(finnishCompany);
        SalesObject phone = new Product("NOKIA 2110");
        SalesObject fixingService = new Service("NOKIA 2110 fixing");
        finnishCompany.add(phone);
        finnishCompany.add(fixingService);
        SalesObject car = new Product("NON EXISTING CAR");
        SalesObject invoice = new Service("FAKE INVOICE");
        estonianCompany.add(car);
        estonianCompany.add(invoice);

        world = worldService.saveWorld(world);

        assertNotNull(world.getId());
    }
}
