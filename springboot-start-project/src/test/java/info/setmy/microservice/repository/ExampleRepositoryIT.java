package info.setmy.microservice.repository;

import info.setmy.microservice.dal.ExampleRepository;
import info.setmy.microservice.domain.ExampleModel;
import info.setmy.microservice.it.SpringBase;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


class ExampleRepositoryIT extends SpringBase {

    @Autowired
    private ExampleRepository exampleRepository;

    @Test
    @Transactional
    void testFindById() throws Exception {
        ExampleModel exampleModel = exampleRepository.findById(1L).orElse(null);
        assertThat(exampleModel).isNotNull();
        assertThat(exampleModel.getGeom()).isEqualTo(new WKTReader().read("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))"));
    }
}
