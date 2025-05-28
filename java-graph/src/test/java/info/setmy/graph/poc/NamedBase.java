package info.setmy.graph.poc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class NamedBase implements Named {

    private final String name;
}
