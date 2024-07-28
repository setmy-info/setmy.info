package info.setmy.stealer.models.steps;

import info.setmy.stealer.models.Repository;
import info.setmy.stealer.models.StepConfig;

public interface Step {

    Step setStepConfig(StepConfig stepConfig);

    Step setRepository(Repository repository);

    void execute();
}
