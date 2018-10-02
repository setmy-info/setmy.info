package info.setmy.microservice.dao;

import info.setmy.microservice.models.ExampleModel;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public interface IExampleDao {

    ExampleModel save(final ExampleModel example);
}
