package info.setmy.crawler.picocli;

import info.setmy.crawler.elt.services.GuiceService;
import info.setmy.crawler.elt.services.TransformsService;
import lombok.extern.log4j.Log4j2;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;

@Log4j2
@Command(name = "subCommand", description = "Sub")
public class SubCommand implements Runnable {

    private GuiceService guiceService;
    private TransformsService transformsService;

    @Option(names = "--workingDirectory", required = false)
    private File workingDirectory;

    @Option(names = "--homeDirectory", required = false)
    private File homeDirectory;

    @Override
    public void run() {
        guiceService = new GuiceService(homeDirectory, workingDirectory)
            .init();
        transformsService = guiceService.getInjector().getInstance(TransformsService.class);
        transformsService.csvToDb();

        log.info("SUB command executed!");
        log.info("Working dir = " + workingDirectory);
        log.info("Home dir = " + homeDirectory);
        // throw new CommandLine.ExitCode(2);
    }
}
