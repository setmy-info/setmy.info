package info.setmy.models.textfunctions.functions;

import java.util.ArrayList;
import java.util.List;

public class Parameters {

    private final List<Parameter> parameterList = new ArrayList<>();

    public Parameter get(final int index) {
        return parameterList.get(index);
    }
}
