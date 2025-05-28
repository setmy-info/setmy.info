package info.setmy.graph.poc;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Node implements Named {

    @EqualsAndHashCode.Include
    @ToString.Include
    private final String name;
}
