package info.setmy.functions;

import java.util.Optional;
import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.isBlank;

public final class OptionalFunctions {

    public static final Function<String, String> blankAsNull = s -> isBlank(s) ? null : s;

    public static Optional<String> blankToEmpty(Optional<String> optional) {
        return optional.map(blankAsNull);
    }

    public static Optional<Boolean> emptyToFalse(Optional<Boolean> optional) {
        if (optional.isEmpty()) {
            return Optional.of(false);
        }
        return optional;
    }

    //<editor-fold defaultstate="collapsed" desc="Private constructor">
    private OptionalFunctions() {
    }
    //</editor-fold>
}
