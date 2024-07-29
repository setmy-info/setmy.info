package info.setmy.stealer.steps;

public class SVCCloneStep extends BaseStep implements Step {

    @Override
    public void execute() {
        repository.getVcs().doClone();
    }
}
