package info.setmy.stealer.models.services;

import info.setmy.stealer.models.config.RepositoryConfig;
import info.setmy.vcs.Vcs;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RepositoryService {

    private final RepositoryConfig repositoryConfig;

    private final Vcs vcs;
}
