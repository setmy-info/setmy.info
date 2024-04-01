package info.setmy.plugins;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PRE_INTEGRATION_TEST;

@Mojo(name = "start", defaultPhase = PRE_INTEGRATION_TEST)
public class ArtemisStartMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        ArtemisServerService artemisServerService = ArtemisServerService
            .getInstance()
            .init()
            .start();
        project.getProperties().put("artemisServerService", artemisServerService);
    }
}
