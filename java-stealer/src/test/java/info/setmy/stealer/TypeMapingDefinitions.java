package info.setmy.stealer;

import info.setmy.vcs.RepoType;
import io.cucumber.java.ParameterType;

import static info.setmy.vcs.RepoType.valueOf;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class TypeMapingDefinitions {

    @ParameterType(".*")
    public RepoType repoType(final String repoTypeString) {
        return valueOf(repoTypeString.toUpperCase());
    }
}
