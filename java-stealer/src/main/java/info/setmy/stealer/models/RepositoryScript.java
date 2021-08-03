package info.setmy.stealer.models;

import info.setmy.stealer.models.config.Repository;
import info.setmy.stealer.models.steps.Step;
import info.setmy.vcs.Vcs;
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
public class RepositoryScript {

    private final Repository repository;

    private final Vcs vcs;

    private final List<Step> steps = new ArrayList<>();

    public void addStep(final Step step) {
        steps.add(step.setRepositoryScript(this));
    }

    public void execute() {
        steps.forEach(Step::execute);
    }
}
