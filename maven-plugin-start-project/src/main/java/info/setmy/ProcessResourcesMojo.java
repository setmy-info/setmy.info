package info.setmy;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mojo(name = "process-resources", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class ProcessResourcesMojo extends AbstractMojo {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Parameter(property = "exampleValue", defaultValue = "Default example value")
    private String exampleValue;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Logging");
        log.info("Executed with {}", exampleValue);
    }

    public String getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(String exampleValue) {
        this.exampleValue = exampleValue;
    }
}
