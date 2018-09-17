package ee.pub;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "starter", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ModelMojo extends AbstractMojo {

    @Parameter(property = "exampleValue", defaultValue = "Default example value")
    private String exampleValue;

    @Override
    public void execute() throws MojoExecutionException {
    }

    public String getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(String exampleValue) {
        this.exampleValue = exampleValue;
    }
}
