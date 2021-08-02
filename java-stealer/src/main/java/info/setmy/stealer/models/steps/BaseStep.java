package info.setmy.stealer.models.steps;

import info.setmy.stealer.models.RepositoryScript;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseStep implements Step {

    protected RepositoryScript repositoryScript;

    protected String stepData;

    @Override
    public Step setRepositoryScript(final RepositoryScript repositoryScript) {
        this.repositoryScript = repositoryScript;
        return this;
    }
}
