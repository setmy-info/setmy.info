package info.setmy.stealer;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

@NoArgsConstructor(access = PRIVATE)
public final class FolderToolsUtils {

    public static String byOS(final String inputString) {
        return IS_OS_WINDOWS ? toWindowsPath(inputString) : inputString;
    }

    public static String toWindowsPath(final String inputString) {
        return IS_OS_WINDOWS ? inputString.replace("/", "\\") : inputString;
    }

    public static String toUnixPath(final String inputString) {
        return IS_OS_WINDOWS ? inputString.replace("\\", "/") : inputString;
    }
}
