package info.setmy.csv;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
public class ParsedRecord {
    private final BigDecimal number;
    private final LocalDateTime localDateTime;
    private final String value;
}
