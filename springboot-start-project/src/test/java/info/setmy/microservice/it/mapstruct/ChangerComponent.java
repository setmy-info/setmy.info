package info.setmy.microservice.it.mapstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangerComponent {

    public String change(String in) {
        return "That was in: " + in;
    }
}
