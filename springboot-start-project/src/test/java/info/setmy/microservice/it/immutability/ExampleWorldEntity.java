package info.setmy.microservice.it.immutability;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

// @Value - like @Data that is also @ToString and @EqualsAndHashCode - these two can query all data from DB when used with ORM.
@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ExampleWorldEntity {

    @With
    private final String foo;
    private final Long bar;
    private final BigDecimal baz;
    private final boolean done;
    @With
    private final String plain;
    private final List<ExampleCountry> countries;
}
