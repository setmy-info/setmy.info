package info.setmy.stealer.models.steps;

import info.setmy.stealer.models.Repository;
import info.setmy.stealer.models.StepConfig;
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
