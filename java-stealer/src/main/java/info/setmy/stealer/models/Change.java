package info.setmy.stealer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Change {

    private final String pattern;
    private final String replacement;
}
