package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.HomeDirectory;
import info.setmy.crawler.elt.models.WorkingDirectory;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class GlobalConfigService {

    private final HomeDirectory homeDirectory;
    private final WorkingDirectory workingDirectory;
}
