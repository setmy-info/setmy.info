package info.setmy.crawler.picocli;

import info.setmy.crawler.elt.models.GuiceCreation;
import info.setmy.crawler.elt.services.GuiceService;
import info.setmy.crawler.elt.services.CsvDbService;
import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;

import java.io.File;

import static com.google.inject.Key.get;
import static com.google.inject.name.Names.named;

@Getter
@Setter
public abstract class SubBase {

    protected GuiceService guiceService;
    protected Runnable scvDb;

    @CommandLine.Option(names = "--workingDirectory", required = true)
    protected File workingDirectory;

    @CommandLine.Option(names = "--homeDirectory", required = true)
    protected File homeDirectory;

    protected void init() {
        final String commandName = this.getClass().getAnnotation(CommandLine.Command.class).name();
        init(commandName);
    }

    protected void init(final String name) {
        guiceService = GuiceService.newGuiceService(
                GuiceCreation.builder()
                    .homeDirectory(homeDirectory)
                    .workingDirectory(workingDirectory)
                    .build()
            )
            .init();
        scvDb = guiceService.getInjector().getInstance(get(CsvDbService.class, named(name)));
    }
}
