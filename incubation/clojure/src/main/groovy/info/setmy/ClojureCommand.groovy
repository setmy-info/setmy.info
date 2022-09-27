package info.setmy

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters
import clojure.lang.RT

@Command(
    name = 'clojure',
    description = '...',
    mixinStandardHelpOptions = true
)
class ClojureCommand implements Runnable {

    private static final String MAIN_CLJ = "info/setmy/main.clj";

    private static String[] ARGS;

    @Option(names = ['-v', '--verbose'], description = 'verbose...')
    boolean verbose

    @Option(names = ['-m', '--more'], description = 'more...')
    String more

    static void main(String[] args) throws Exception {
        ARGS = args;
        PicocliRunner.run(ClojureCommand, args)
    }

    void run() {
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
}
