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

    @CommandLine.Option(names = ["-o", "--ooo"], required = false, paramLabel = "sms", description = "sms usage number")
    private String ooo;

    final ClojureService clojureService

    Command(final ClojureService clojureService) {
        this.clojureService = clojureService
    }

    @Override
    Integer call() {
        log.info("Logging works in command {}", ooo)
        clojureService.executeClojure()
        return 0;
    }
}
