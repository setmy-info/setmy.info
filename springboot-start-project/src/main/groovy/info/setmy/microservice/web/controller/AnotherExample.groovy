package info.setmy.microservice.web.controller

import info.setmy.microservice.clojure.service.ClojureExec
import info.setmy.microservice.clojure.service.ClojureService
import info.setmy.microservice.mapper.ExampleMapper
import info.setmy.microservice.service.ExampleService
import info.setmy.microservice.web.dto.ExampleDTO
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.ALL_VALUE
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Tag(name = "AnotherExampleController", description = "Another Example Controller")
@RequestMapping(
    path = "/api/anotherExample",
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE
)
@RestController
@Validated
class AnotherExample {

    private static final String DEFAULT_MAIN_CLJ_SCRIPT = "info/setmy/core.clj"
    private static final String DEFAULT_NAME_SPACE = "info.setmy.core"
    private static final String DEFAULT_MAIN_NAME = "-main"

    private final static Logger log = LogManager.getLogger(AnotherExample)

    private final ExampleService exampleService

    private final ExampleMapper exampleMapper

    private final ClojureService clojureService

    AnotherExample(
        ExampleService exampleService,
        ExampleMapper exampleMapper
    ) {
        this.exampleService = exampleService
        this.exampleMapper = exampleMapper
    }

    @GetMapping(consumes = ALL_VALUE)
    ExampleDTO example() {
        log.info("Example GET called");
        def clojureExec = ClojureExec.builder()
            .ns(DEFAULT_NAME_SPACE)
            .scriptName(DEFAULT_MAIN_CLJ_SCRIPT)
            .mainFunctionName(DEFAULT_MAIN_NAME)
            .args([])
            .build()
        clojureService.exec(clojureExec)
        return exampleMapper.toDto(exampleService.getExampleModel());
    }
}
