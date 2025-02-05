package info.setmy.arch.unit;

import java.util.List;

public final class JpaPackages implements PackageGroup {

    @Override
    public List<String> packages() {
        return List.of("org.springframework..", "org.springframework.boot..");
    }
}
