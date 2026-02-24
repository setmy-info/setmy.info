package info.setmy.microservice.it.mapstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CopyComponent {

    private final ExampleAnotherMapper exampleAnotherMapper;

    public DestinationExample toDestinationExample(SourceExample sourceExample) {
        return exampleAnotherMapper.toDestinationExample(sourceExample);
    }
}
