package info.setmy.arch.unit;

import java.util.List;

public final class GenericPackageGroup implements PackageGroup {

    private final List<String> packages;

    public GenericPackageGroup(String... packageNames) {
        this.packages = List.of(packageNames);
    }

    @Override
    public List<String> packages() {
        return packages;
    }
}
