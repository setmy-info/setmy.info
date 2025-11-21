package info.setmy.crawler.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class CSVRecord {
    private final String name;
    private final String url;
}
