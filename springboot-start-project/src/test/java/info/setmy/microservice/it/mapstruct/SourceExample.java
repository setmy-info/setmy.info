package info.setmy.microservice.it.mapstruct;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SourceExample {
    private String commonField;
    private String anotherField;
    private String changed;
}
