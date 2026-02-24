package info.setmy.arch.unit;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class SinglePackageGroup implements PackageGroup {

    private final String packageName;

    @Override
    public List<String> packages() {
        return List.of(packageName);
    }
}
