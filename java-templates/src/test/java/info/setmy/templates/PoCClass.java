package info.setmy.templates;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class PoCClass {

    private final String className;
    private final List<String> attributeNames = new ArrayList<>();
}
