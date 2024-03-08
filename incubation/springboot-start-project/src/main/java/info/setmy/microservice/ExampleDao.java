package info.setmy.microservice;

import jakarta.inject.Named;

@Named("exampleDao")
public class ExampleDao {

    public ExampleModel getExampleModel() {
        final ExampleModel model = new ExampleModel();
        model.setText("Hello World!");
        return model;
    }
}
