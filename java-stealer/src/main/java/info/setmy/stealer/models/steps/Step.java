package info.setmy.stealer.models.steps;

import info.setmy.stealer.models.RepositoryScript;

public interface Step {

    Step setRepositoryScript(RepositoryScript repositoryScript);

    void execute();
}
