package info.setmy.microservice.web.controller;

import info.setmy.microservice.exception.MicroServiceException;
import info.setmy.microservice.mapper.ExampleMapper;
import info.setmy.microservice.service.ExampleService;
import info.setmy.microservice.web.dto.ExampleDTO;
import info.setmy.microservice.web.exception.ServiceUnavailableException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static info.setmy.microservice.web.constant.ApiConstants.API_BASE;
import static info.setmy.microservice.web.constant.ErrorConstants.KEY_FOR_ANOTHER_ERROR_KEY_VALUE;
import static info.setmy.microservice.web.constant.ErrorConstants.KEY_FOR_SOME_ERROR_KEY_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" -i http://localhost:8080/api/example
@OpenAPIDefinition(
    info = @Info(
        title = "Example API",
        version = "v1.2.3",
        description = "This is API"
    )
)
@Tag(name = "ExampleController", description = "Example Controller")
@Log4j2
@RestController
@RequestMapping(
    path = ExampleController.EXAMPLE_REST_RESOURCE,
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE
)
@Validated
@RequiredArgsConstructor
public class ExampleController {

    public static final String EXAMPLE_REST_RESOURCE = API_BASE + "/example";

    private final ExampleService exampleService;

    private final ExampleMapper exampleMapper;

    @GetMapping
    public ExampleDTO example() {
        log.info("Example GET called");
        return exampleMapper.toDto(exampleService.getExampleModel());
    }

    @Operation(summary = "Summary line about method", description = "Description about method functionality")
    @PostMapping
    public ExampleDTO example(@Valid @RequestBody final ExampleDTO exampleDTO) {
        log.info("Example POST called with {}", exampleDTO);
        return exampleMapper.toDto(
            exampleMapper.toEntity(exampleDTO)
        );
    }

    //curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" -d '{"exampleString": "Example string that should not go into DB"}' -i http://localhost:8080/api/example/rollback
    @PostMapping(value = "rollback")
    public ExampleDTO rollback(@RequestBody final ExampleDTO exampleDTO) {
        log.info("Example POST called with {} to be rolled back", exampleDTO);
        try {
            exampleService.doWithRollback(exampleMapper.toEntity(exampleDTO), true);
        } catch (MicroServiceException e) {
            throw new ServiceUnavailableException(KEY_FOR_SOME_ERROR_KEY_VALUE, e);
        }
        return exampleMapper.toDto(
            exampleMapper.toEntity(exampleDTO)
        );
    }

    //curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" -d '{"exampleString": "Example string that should go into DB"}' -i http://localhost:8080/api/example/norollback
    @PostMapping(value = "norollback")
    public ExampleDTO noRollback(@RequestBody final ExampleDTO exampleDTO) {
        log.info("Example POST called with {} to be not rolled back", exampleDTO);
        try {
            exampleService.doWithRollback(exampleMapper.toEntity(exampleDTO), false);
        } catch (MicroServiceException e) {
            throw new ServiceUnavailableException(KEY_FOR_ANOTHER_ERROR_KEY_VALUE, e);
        }
        return exampleMapper.toDto(
            exampleMapper.toEntity(exampleDTO)
        );
    }
}
