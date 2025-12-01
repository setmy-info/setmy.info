package info.setmy.crawler.elt.models;

import lombok.Builder;

import java.io.File;

@Builder(toBuilder = true)
public record GuiceCreation(
    File homeDirectory,
    File workingDirectory
) {
}
