package info.setmy

import clojure.lang.RT
import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Command(
    name = 'clojure',
    description = '...',
    mixinStandardHelpOptions = true
)
class ClojureCommand implements Runnable {

    static Logger LOG = LoggerFactory.getLogger(ClojureCommand)

    private static final String MAIN_CLJ = "info/setmy/main.clj";

    private static String[] ARGS;

    @Option(names = ['-v', '--verbose'], description = 'verbose...')
    boolean verbose

    @Option(names = ['-m', '--more'], description = 'more...')
    String more

    private final BarService barService

    ClojureCommand(final BarService barService) {
        this.barService = barService
    }

    static void main(String[] args) throws Exception {
        ARGS = args;
        LOG.info("Works!")
        PicocliRunner.run(ClojureCommand, args)
    }

    void run() {
        barService.getInjectionWorks()
        // business logic here
        if (verbose) {
            println "Hi!"
        }
        try {
            RT.loadResourceScript(MAIN_CLJ);
            RT.var("info.setmy.main", "main").invoke(ARGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static getFoo() {
        "Foo"
    }
}
