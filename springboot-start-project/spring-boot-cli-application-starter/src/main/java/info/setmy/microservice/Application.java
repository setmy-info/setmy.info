package info.setmy.microservice;

import info.setmy.microservice.service.EntryPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner, ExitCodeGenerator {

    private final EntryPointService entryPointService;

    private int exitCode;

    public static void main(final String[] args) {
        System.exit(
            SpringApplication.exit(
                new SpringApplicationBuilder(Application.class)
                    // No web server, even when a web library is on the class path
                    .web(WebApplicationType.NONE)
                    .run(args)
            )
        );
    }

    @Override
    public void run(final String... args) throws Exception {
        exitCode = entryPointService.run(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
