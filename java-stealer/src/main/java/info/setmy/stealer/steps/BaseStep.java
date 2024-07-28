package info.setmy.stealer.steps;

import info.setmy.stealer.Repository;
import info.setmy.stealer.StepConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseStep implements Step {

    protected Repository repository;

    protected StepConfig stepConfig;
}
