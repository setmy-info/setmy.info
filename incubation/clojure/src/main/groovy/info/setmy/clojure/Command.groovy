package info.setmy.clojure

import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import picocli.CommandLine

import java.util.concurrent.Callable

@Slf4j
@Profile("!test")
@Component
@CommandLine.Command(name = "clojure", mixinStandardHelpOptions = true, version = "0.0.0-SNAPSHOT", description = "Clojure learning")
class Command implements Callable<Integer> {

    private static final String DEFAULT_MAIN_CLJ_SCRIPT = "info/setmy/main.clj"
    private static final String DEFAULT_NAME_SPACE = "info.setmy.main"
    private static final String DEFAULT_MAIN_NAME = "default-main"

    @CommandLine.Option(names = ["-n", "--namespace"], required = false, paramLabel = "namespace", description = "namespace")
    private Optional<String> namespace;

    @CommandLine.Option(names = ["-s", "--scriptName"], required = false, paramLabel = "scriptName", description = "scriptName")
    private Optional<String> scriptName;

    @CommandLine.Option(names = ["-m", "--mainFunctionName"], required = false, paramLabel = "mainFunctionName", description = "mainFunctionName")
    private Optional<String> mainFunctionName;

    final ClojureService clojureService

    private String[] args

    Command(final ClojureService clojureService) {
        this.clojureService = clojureService
    }

    @Override
    Integer call() {
        def clojureExec = ClojureExec.builder()
            .ns(namespace.orElse(DEFAULT_NAME_SPACE))
            .scriptName(scriptName.orElse(DEFAULT_MAIN_CLJ_SCRIPT))
            .mainFunctionName(mainFunctionName.orElse(DEFAULT_MAIN_NAME))
            .args(args)
            .build()
        log.info("Executing {}", clojureExec)
        clojureService.exec(clojureExec)
        return 0;
    }
}
