package info.setmy.microservice.dal;

import info.setmy.microservice.domain.ExampleModel;

public interface IExampleDao {

    ExampleModel save(final ExampleModel example);
}
