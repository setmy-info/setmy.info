package info.setmy.stealer.cli.models;

import info.setmy.stealer.cli.StealerConfigService;
import info.setmy.stealer.models.StealerConfig;
import info.setmy.stealer.services.StealerService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;


@RequiredArgsConstructor
public class StealerCallable extends StealerCommandLineParameters implements Callable<Integer> {

    private final StealerService stealerService;

    private final StealerConfigService stealerConfigService;

    @Override
    public Integer call() throws Exception {
        final StealerConfig stealerConfig = stealerConfigService.getConfig();
        stealerService.steal(stealerConfig);
        return 0;
    }
}
