package info.setmy.crawler.elt.services;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import info.setmy.crawler.elt.models.GuiceCreation;
import info.setmy.crawler.elt.models.HomeDirectory;
import info.setmy.crawler.elt.models.WorkingDirectory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

import static com.google.inject.Scopes.SINGLETON;
import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class GuiceService extends AbstractModule {

    private final File workingDirectoryPath;
    private final File homeDirectoryPath;

    private WorkingDirectory workingDirectory;
    private HomeDirectory homeDirectory;

    private Injector injector;

    public static GuiceService newGuiceService(final GuiceCreation creation) {
        return new GuiceService(
            creation.workingDirectory(),
            creation.homeDirectory()
        );
    }

    public GuiceService init() {
        initWorkingDirectory();
        initHomeDirectory();
        injector = Guice.createInjector(this);
        return this;
    }

    @Override
    protected void configure() {
        bind(WorkingDirectory.class).toInstance(workingDirectory);
        bind(HomeDirectory.class).toInstance(homeDirectory);

        bind(GlobalConfigService.class).in(SINGLETON);
        //bind(ScvDbService.class).in(SINGLETON);
        bind(ScvDbService.class)
            .annotatedWith(Names.named("scvDb"))
            .to(ScvDbService.class)
            .in(SINGLETON);
        bind(DataSourceFactoryService.class).in(SINGLETON);
        bind(HibernateService.class).in(SINGLETON);
        bind(JOOQService.class).in(SINGLETON);
    }

    private void initWorkingDirectory() {
        workingDirectory = new WorkingDirectory(workingDirectoryPath)
            .init();
    }

    private void initHomeDirectory() {
        homeDirectory = new HomeDirectory(homeDirectoryPath)
            .init();
    }
}
