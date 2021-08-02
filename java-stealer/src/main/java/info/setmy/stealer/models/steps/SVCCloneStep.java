package info.setmy.stealer.models.steps;

public class SVCCloneStep extends BaseStep implements Step {

    @Override
    public void execute() {
        repositoryScript.getVcs().doClone();
    }
}
