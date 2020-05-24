package info.setmy.microservice.services;

import info.setmy.microservice.dao.ExampleDao;
import info.setmy.microservice.dao.ExampleRepository;
import info.setmy.microservice.dao.JDBCExampleDao;
import info.setmy.microservice.dao.JPAExampleDao;
import info.setmy.microservice.dao.PersonRepository;
import info.setmy.microservice.example.model.Person;
import info.setmy.microservice.models.ExampleModel;
import info.setmy.microservice.properties.ExampleProperties;
import javax.inject.Named;
import javax.transaction.Transactional;
import org.infinispan.Cache;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("exampleService")
public class ExampleService {

    private final ExampleDao exampleDao;

    private final ExampleProperties exampleProperties;

    private final ExampleRepository exampleRepository;

    private final JDBCExampleDao jdbcExampleDao;

    private final JPAExampleDao jpaExampleDao;

    private final PersonRepository personRepository;

    //private final DozerService dozerService;
    //private final Cache cache;

    public ExampleService(final ExampleDao exampleDao,
            final ExampleProperties exampleProperties,
            //final DozerService dozerService,
            final ExampleRepository exampleRepository,
            final JDBCExampleDao jdbcExampleDao,
            final JPAExampleDao jpaExampleDao,
            final PersonRepository personRepository/*,
            final Cache cache*/) {
        this.exampleDao = exampleDao;
        this.exampleProperties = exampleProperties;
        //this.dozerService = dozerService;
        this.exampleRepository = exampleRepository;
        this.jdbcExampleDao = jdbcExampleDao;
        this.jpaExampleDao = jpaExampleDao;
        this.personRepository = personRepository;
        //this.cache = cache;
    }

    @Transactional
    public ExampleModel getExampleModel() {
        String counterKkey = "counter";
        Integer counter = 0;//(Integer) cache.get(counterKkey);
        /*if (counter == null) {
            counter = 1;
            cache.put(counterKkey, counter);
        } else {
            counter++;
        }*/
        //insertData();
        //final ExampleModel model = exampleDao.getExampleModel();
        final ExampleModel model = exampleRepository.findAll().get(0);
        final ExampleModel newModel = model;//dozerService.copyExampleModel(model);
        return newModel;
    }

    private void insertData() {
        ExampleModel model = new ExampleModel();
        model.setText("Hello World from JPA inserted data!");
        exampleRepository.save(model);

        model = new ExampleModel();
        model.setId(10L);// Not needed with autoincrement DB
        model.setText("Hello World from JDBC inserted data vol 2!");
        jdbcExampleDao.save(model);

        model = new ExampleModel();
        model.setText("Hello World from JPA inserted data vol 3!");
        jpaExampleDao.save(model);
    }
}
