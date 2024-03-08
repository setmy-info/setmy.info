package info.setmy.microservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" -i http://localhost:8080/api/example
@Log4j2
@RestController
@RequestMapping(
    path = ExampleController.EXAMPLE_REST_RESOURCE,
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ExampleController {

    public static final String EXAMPLE_REST_RESOURCE = "/api/example";

    private final ExampleService exampleService;

    @GetMapping(value = "")
    public ExampleModel example() {
        log.info("Spring controller called!!");
        return exampleService.getExampleModel();
    }
}
