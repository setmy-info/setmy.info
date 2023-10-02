package info.setmy.microservice.dao;

import info.setmy.microservice.models.ExampleModel;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named("jpaExampleDao")
public class JPAExampleDao extends JPABaseDao implements IExampleDao {

    final Logger log = LogManager.getLogger(getClass());

    @Override
    public ExampleModel save(final ExampleModel example) {
        log.debug("Saving example");
        entityManager.persist(example);
        entityManager.flush();
        return example;
    }
}
