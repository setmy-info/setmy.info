package info.setmy.models;

import info.setmy.properties.ExampleProperties;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleModel {

    private String text;

    private ExampleProperties exampleProperties;

    public ExampleProperties getExampleProperties() {
        return exampleProperties;
    }

    public void setExampleProperties(ExampleProperties exampleProperties) {
        this.exampleProperties = exampleProperties;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ExampleModel{" + "text=" + text + ", exampleProperties=" + exampleProperties + '}';
    }
}
