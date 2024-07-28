package info.setmy.stealer.models;

import info.setmy.stealer.models.steps.Step;
import info.setmy.vcs.Vcs;
import info.setmy.vcs.models.RepositoryConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Getter
@Builder
@AllArgsConstructor
public class Repository {

    private final RepositoryConfig repositoryConfig;

    private final Vcs vcs;

    private final List<Step> steps = new ArrayList<>();

    public void addStep(final Step step) {
        steps.add(step.setRepository(this));
    }

    public void execute() {
        steps.forEach(Step::execute);
    }
}
