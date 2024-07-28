package info.setmy.stealer.models.services;

import info.setmy.vcs.Vcs;
import info.setmy.vcs.models.CloningConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RepositoryService {

    private final CloningConfig cloningConfig;

    private final Vcs vcs;
}
