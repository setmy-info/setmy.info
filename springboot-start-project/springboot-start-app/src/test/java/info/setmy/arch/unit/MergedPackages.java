package info.setmy.arch.unit;

import java.util.List;

public final class MergedPackages implements PackageGroup {

    private final List<PackageGroup> groups;

    public MergedPackages(PackageGroup... groups) {
        this.groups = List.of(groups);
    }

    @Override
    public List<String> packages() {
        return groups.stream()
            .flatMap(group -> group.packages().stream())
            .toList();
    }
}
