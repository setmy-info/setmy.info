package info.setmy.stealer.cli;

import info.setmy.stealer.cli.models.StealerCommandLineParameters;
import info.setmy.stealer.models.StealerConfig;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StealerValidator {

    private final static StealerValidator INSTANCE = new StealerValidator();

    public static StealerValidator getInstance() {
        return INSTANCE;
    }

    public StealerCommandLineParameters validate(final StealerCommandLineParameters stealerCommandLineParameters) {
        //TODO :
        return stealerCommandLineParameters;
    }

    public StealerConfig validate(final StealerConfig stealerConfig) {
        //TODO :
        return stealerConfig;
    }
}
