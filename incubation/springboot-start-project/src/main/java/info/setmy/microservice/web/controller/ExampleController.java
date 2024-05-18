package info.setmy.microservice.web.controller;

import info.setmy.microservice.exception.MicroServiceException;
import info.setmy.microservice.mapper.ExampleMapper;
import info.setmy.microservice.service.ExampleService;
import info.setmy.microservice.web.dto.ExampleDTO;
import info.setmy.microservice.web.exception.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import static info.setmy.microservice.web.constant.ApiConstants.API_BASE;
import static info.setmy.microservice.web.constant.ErrorConstants.KEY_FOR_ANOTHER_ERROR_KEY_VALUE;
import static info.setmy.microservice.web.constant.ErrorConstants.KEY_FOR_SOME_ERROR_KEY_VALUE;
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

    @GetMapping
    public ExampleDTO example() {
        log.info("Example GET called");
        return exampleMapper.toDto(exampleService.getExampleModel());
    }

    @PostMapping
    public ExampleDTO example(@RequestBody final ExampleDTO exampleDTO) {
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
