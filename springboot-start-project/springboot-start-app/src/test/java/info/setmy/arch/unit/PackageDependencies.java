package info.setmy.arch.unit;

import lombok.Builder;

@Builder
public record PackageDependencies(String packageName, String[] allowedOnlyDependencies) {
}
