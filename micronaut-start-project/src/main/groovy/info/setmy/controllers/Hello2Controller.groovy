package info.setmy.controllers

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Produces
import io.micronaut.http.MediaType

/**
 * Example Groovy controller.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Controller("/hello2")
class Hello2Controller {

    @Get("/")
    @Produces(MediaType.TEXT_PLAIN)
    String index() {
        return "Hello World"
    }
}
