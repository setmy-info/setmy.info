package info.setmy.crawler.elt.services;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import info.setmy.crawler.elt.models.HomeDirectory;
import info.setmy.crawler.elt.models.WorkingDirectory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

import static com.google.inject.Scopes.SINGLETON;

@Getter
@RequiredArgsConstructor
public final class GuiceService extends AbstractModule {

    private final File workingDirectoryPath;
    private final File homeDirectoryPath;

    private WorkingDirectory workingDirectory;
    private HomeDirectory homeDirectory;

    private Injector injector;

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
        bind(TransformsService.class).in(SINGLETON);
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
