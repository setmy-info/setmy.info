package info.setmy.models.csv;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class CSVConfig {

    @NonNull
    private final String charsetName;
    private final char delimiter;
    private final char quote;
    private final long skip;
}
