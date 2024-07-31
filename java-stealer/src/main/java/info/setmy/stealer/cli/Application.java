package info.setmy.stealer.cli;

import info.setmy.stealer.cli.models.StealerCallable;
import info.setmy.stealer.services.StealerService;
import picocli.CommandLine;

import static java.lang.System.exit;

public class Application {

    public static void main(final String... args) {
        exit(new CommandLine(new StealerCallable(new StealerService(), new StealerConfigService())).execute(args));
    }
}
