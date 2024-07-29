package info.setmy.stealer.cucumber;

import info.setmy.vcs.models.RepoType;
import io.cucumber.java.ParameterType;

import java.net.MalformedURLException;
import java.net.URL;

import static info.setmy.vcs.models.RepoType.valueOf;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class TypeMapingDefinitions {

    @ParameterType(".*")
    public RepoType repoType(final String repoTypeString) {
        return valueOf(repoTypeString.toUpperCase());
    }

    @ParameterType(".*")
    public URL urlType(final String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
