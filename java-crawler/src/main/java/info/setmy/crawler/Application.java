package info.setmy.crawler;

import info.setmy.crawler.picocli.CsvDb;
import lombok.extern.log4j.Log4j2;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Log4j2
@Command(name = "someMainCommand",
    description = "Main command",
    subcommands = {CsvDb.class})
public class Application implements Runnable {

    @Option(names = {"--help", "-h"}, usageHelp = true, description = "Show help")
    private boolean helpRequested = false;

    @Override
    public void run() {
        log.info("Main command executed");
    }

    static void main(final String[] args) {
        int exitCode = new CommandLine(new Application())
            .execute(args);
        System.exit(exitCode);
    }
}
