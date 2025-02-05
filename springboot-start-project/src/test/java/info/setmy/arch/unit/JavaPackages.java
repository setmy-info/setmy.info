package info.setmy.arch.unit;

import java.util.List;

public final class JavaPackages implements PackageGroup {

    @Override
    public List<String> packages() {
        return List.of("java..", "javax..");
    }
}
