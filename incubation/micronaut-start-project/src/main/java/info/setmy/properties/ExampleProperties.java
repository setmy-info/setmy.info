package info.setmy.properties;

import io.micronaut.context.annotation.ConfigurationProperties;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@ConfigurationProperties("example.property")
public class ExampleProperties {

    @NotBlank
    private String name;

    @NotBlank
    private String other;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "ExampleProperties{" + "name=" + name + ", other=" + other + '}';
    }
}
