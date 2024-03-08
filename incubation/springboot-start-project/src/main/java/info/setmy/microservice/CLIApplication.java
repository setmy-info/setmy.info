package info.setmy.microservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class CLIApplication implements CommandLineRunner, ExitCodeGenerator {

    private final ExampleService exampleService;

    private int exitCode;

    public static void main(final String[] args) {
        System.exit(
            SpringApplication.exit(
                SpringApplication.run(CLIApplication.class, args)
            )
        );
    }

    @Override
    public void run(final String... args) throws Exception {
        var exampleModel = exampleService.getExampleModel();
        exitCode = 0;
        // Also changes in exitCode
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
