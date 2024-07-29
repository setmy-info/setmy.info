package info.setmy.vcs;

import info.setmy.vcs.models.CommandData;
import info.setmy.vcs.models.RepositoryConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static info.setmy.vcs.Constants.DEFAULT_COMMAND_DATA;

@Getter
@RequiredArgsConstructor
public class GitVcs extends VcsBase implements Vcs {

    private final RepositoryConfig repositoryConfig;

    private final CommandData commandData = DEFAULT_COMMAND_DATA.toBuilder()
        .command("git")
        .build();
}
