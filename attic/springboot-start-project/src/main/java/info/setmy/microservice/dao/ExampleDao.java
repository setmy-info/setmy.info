package info.setmy.microservice.dao;

import info.setmy.microservice.models.ExampleModel;
import jakarta.inject.Named;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Named("exampleDao")
public class ExampleDao {

    public ExampleModel getExampleModel() {
        final ExampleModel model = new ExampleModel();
        model.setText("Hello World!");
        return model;
    }
}
