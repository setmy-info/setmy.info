package info.setmy.microservice.controllers;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@Controller
@RequestMapping("/listExample")
@RequiredArgsConstructor
public class ListExampleController {

    @GetMapping
    public String getPayments(Model model) {
        log.info("Greeting");
        model.addAttribute("list", Arrays.asList("a", "b", "c"));
        return "listExample";
    }
}
