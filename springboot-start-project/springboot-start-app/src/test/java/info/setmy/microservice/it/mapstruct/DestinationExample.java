package info.setmy.microservice.it.mapstruct;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DestinationExample {
    private String commonField;
    private String differentField;
    private String changed;
}
