package info.setmy.microservice.properties;

import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Configuration("exampleProperties")
public class ExampleProperties {

    private String foo;

    private String bar;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}
