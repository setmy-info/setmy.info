package info.setmy.stealer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Getter
@Builder
@RequiredArgsConstructor
public class Repository {

    private final String name;

    private final String url;

    private final RepoType repoType;
}
