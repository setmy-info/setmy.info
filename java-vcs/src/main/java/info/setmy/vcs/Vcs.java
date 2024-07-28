package info.setmy.vcs;

public interface Vcs {

    void doClone();

    void doCheckout(final String branchName);

    void doFetch();

    void doPull();

    void doPush();
}
