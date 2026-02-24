package info.setmy.arch.unit;

import java.util.List;

public final class JakartaPackages implements PackageGroup {

    @Override
    public List<String> packages() {
        return List.of("jakarta..");
    }
}
