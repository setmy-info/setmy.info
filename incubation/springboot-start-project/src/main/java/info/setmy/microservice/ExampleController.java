package info.setmy.microservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static info.setmy.microservice.ApiConstants.API_BASE;
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

    public static final String EXAMPLE_REST_RESOURCE = API_BASE + "/example";

    private final ExampleService exampleService;

    private final ExampleMapper exampleMapper;

    @GetMapping//(value = "")
    public ExampleDTO example() {
        log.info("Example GET called");
        return exampleMapper.toDto(exampleService.getExampleModel());
    }

    @PostMapping//(value = "")
    public ExampleDTO example(@RequestBody final ExampleDTO exampleDTO) {
        log.info("Example POST called {}", exampleDTO);
        return exampleMapper.toDto(
            exampleMapper.toEntity(exampleDTO)
        );
    }
}
