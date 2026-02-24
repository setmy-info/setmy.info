package info.setmy.arch.unit;

import java.util.List;

public final class SpringPackages implements PackageGroup {

    @Override
    public List<String> packages() {
        return List.of("org.springframework..", "org.springframework.boot..");
    }
}
