package info.setmy.stealer.cli.models;

import info.setmy.stealer.cli.StealerConfigService;
import info.setmy.stealer.cli.StealerValidator;
import info.setmy.stealer.services.StealerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;

@Getter
@RequiredArgsConstructor
public class StealerCallable extends StealerCommandLineParameters implements Callable<Integer> {

    private final StealerService stealerService;

    private final StealerConfigService stealerConfigService;

    private final StealerValidator stealerValidator;

    @Override
    public Integer call() {
        stealerValidator.validate(this);
        stealerService.steal(
            stealerValidator.validate(
                stealerConfigService.getConfig()
            )
        );
        return 0;
    }
}
