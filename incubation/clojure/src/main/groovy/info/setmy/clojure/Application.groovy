package info.setmy.clojure

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@Slf4j
@SpringBootApplication
class Application {

    static void main(String[] args) {
        log.info("Logging works in main")
        SpringApplication.run(Application, args)
    }
}
