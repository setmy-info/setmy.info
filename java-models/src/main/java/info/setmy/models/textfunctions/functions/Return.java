package info.setmy.models.textfunctions.functions;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class Return {

    public static Return newReturn() {
        return new Return();
    }

    public static Return newReturn(final Object... objects) {
        final Return result = new Return();
        stream(objects).forEach(object -> {
            result.returnList.add(object);
        });
        return result;
    }

    private final List<Object> returnList = new ArrayList<>();
}
