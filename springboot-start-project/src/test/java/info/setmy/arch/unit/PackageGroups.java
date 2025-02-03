package info.setmy.arch.unit;

import lombok.NoArgsConstructor;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PackageGroups {

    public static final String[] JAVA_PACKAGES = {"java..", "javax.."};
    public static final String[] JAKARTA_PACKAGES = {"jakarta.."};
    public static final String[] SPRING_PACKAGES = {"org.springframework..", "org.springframework.boot.."};
    public static final String[] JPA_PACKAGES = {"org.hibernate.."};

    public static String[] mergePackages(String[]... packageGroups) {
        return Arrays.stream(packageGroups)
            .flatMap(Arrays::stream)
            .toArray(String[]::new);
    }
}
