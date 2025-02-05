package info.setmy.arch.unit;

import lombok.NoArgsConstructor;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PackageGroups {

    public static final String[] MICROSERVICE_PACKAGES = {"info.setmy.."};

    public static final String[] JAVA_PACKAGES = {"java..", "javax.."};
    public static final String[] LOMBOK_PACKAGES = {"lombok.."};
    public static final String[] JAKARTA_PACKAGES = {"jakarta.."};
    public static final String[] SPRING_PACKAGES = {"org.springframework..", "org.springframework.boot.."};
    public static final String[] JPA_PACKAGES = {"org.hibernate.."};
    public static final String[] SLF_PACKAGES = {"org.slf4j.."};
    public static final String[] APACHE_PACKAGES = new String[]{"org.apache.."};

    public static String[] mergePackages(String[]... packageGroups) {
        return Arrays.stream(packageGroups)
            .flatMap(Arrays::stream)
            .toArray(String[]::new);
    }

    public static String[] toArray(String... names) {
        return Arrays.copyOf(names, names.length);
    }
}
