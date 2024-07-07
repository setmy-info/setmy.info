package info.setmy.microservice.dal;

import info.setmy.microservice.domain.ExampleModel;
import jakarta.inject.Named;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import org.hibernate.annotations.NamedQuery;

@Log4j2
@Named

public class HibernateExampleDao extends HibernateBaseDao implements IExampleDao {

    @Override
    public ExampleModel save(final ExampleModel example) {
        return example;
    }

    public List<ExampleModel> findAll() {
        return getSession()
                .createNamedQuery("findAll", ExampleModel.class)
                .list();
    }
}
