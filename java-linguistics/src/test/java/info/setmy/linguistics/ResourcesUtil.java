package info.setmy.linguistics;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResourcesUtil {

    public static String getTestFileName(final Class<?> testClass, final String fileName) {
        return "./src/test/resources/" + testClass.getSimpleName() + "/" + fileName;
    }
}
