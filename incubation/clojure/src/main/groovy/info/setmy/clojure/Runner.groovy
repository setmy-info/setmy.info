package info.setmy.clojure

import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.ExitCodeGenerator
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import picocli.CommandLine

@Slf4j
@Profile("!test")
@Component
class Runner implements CommandLineRunner, ExitCodeGenerator {

    private final CommandLine.IFactory factory;

    private final Command command;

    private final ClojureService clojureService

    private int exitCode;

    Runner(final CommandLine.IFactory factory, final Command command, final ClojureService clojureService) {
        this.factory = factory
        this.command = command
        this.clojureService = clojureService
    }

    @Override
    void run(final String... args) throws Exception {
        command.args = args
        exitCode = new CommandLine(command, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
