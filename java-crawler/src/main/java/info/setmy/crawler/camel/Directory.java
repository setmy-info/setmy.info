package info.setmy.crawler.camel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Getter
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
public abstract class Directory {

    protected final File directory;
}
