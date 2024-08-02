package info.setmy.plugins;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import static java.util.Objects.isNull;
import static org.apache.maven.plugins.annotations.LifecyclePhase.POST_INTEGRATION_TEST;

@Mojo(name = "stop", defaultPhase = POST_INTEGRATION_TEST)
public class ArtemisStopMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        final ArtemisServerService artemisServerService = (ArtemisServerService) project
            .getProperties()
            .get(
                ArtemisServerService.class.getSimpleName()
            );
        if (!isNull(artemisServerService)) {
            artemisServerService.stop();
        }
    }
}
