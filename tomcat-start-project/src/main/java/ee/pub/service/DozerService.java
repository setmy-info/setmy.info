package ee.pub.service;

import ee.pub.model.Person;
import javax.inject.Inject;
import javax.inject.Named;
import org.dozer.Mapper;

@Named("dozerService")
public class DozerService {

    @Inject
    private Mapper beanMapper;

    public Mapper getBeanMapper() {
        return beanMapper;
    }

    public void setBeanMapper(Mapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    public Person personToPersonNameOnly(final Person person) {
        return beanMapper.map(person, Person.class, "firstNameOnlyCase");
    }

    public Person personToPerson(final Person person) {
        return beanMapper.map(person, Person.class);
    }
}
