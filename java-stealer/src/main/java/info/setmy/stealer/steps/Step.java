package info.setmy.stealer.steps;

import info.setmy.stealer.Repository;
import info.setmy.stealer.StepConfig;

public interface Step {

    Step setStepConfig(StepConfig stepConfig);

    Step setRepository(Repository repository);

    void execute();
}
