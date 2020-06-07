package info.setmy.microservice.services;

import com.github.dozermapper.core.Mapper;
import info.setmy.microservice.models.ExampleModel;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("dozerService")
public class DozerService {

    private final Logger log = LogManager.getLogger(getClass());

    private final Mapper dozerBeanMapper;

    public DozerService(final Mapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public ExampleModel copyExampleModel(final ExampleModel model) {
        //ExampleModel obj = dozerBeanMapper.map(model, ExampleModel.class);// TODO : investigate dozer problems. Probably Dozer doesn't work with JDK14 problem.
        ExampleModel obj = ExampleModel.builder().
                text(model.getText()).
                dateTime(model.getDateTime()).
                build();
        return obj;
    }

    public Mapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }
}
