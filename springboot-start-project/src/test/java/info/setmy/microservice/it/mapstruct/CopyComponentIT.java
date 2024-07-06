package info.setmy.microservice.it.mapstruct;


import info.setmy.microservice.it.SpringBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CopyComponentIT extends SpringBase {

    @Autowired
    CopyComponent copyComponent;

    @Test
    public void testCopyComponent() {
        SourceExample sourceExample = SourceExample.builder()
            .commonField("This is common field")
            .anotherField("This is another field")
            .changed("changed")
            .build();

        DestinationExample destinationExample = copyComponent.toDestinationExample(sourceExample);

        assertThat(destinationExample).isNotNull();
        assertThat(destinationExample.getCommonField()).isEqualTo("This is common field");
        assertThat(destinationExample.getDifferentField()).isEqualTo("This is another field");
        assertThat(destinationExample.getChanged()).isEqualTo("changed");
    }
}
