package info.setmy.microservice.web.validation;

import info.setmy.microservice.web.dto.ExampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.stream.Collectors;

@Log4j2
@Component
public class ExampleDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ExampleDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("Validating ExampleDTO");
        ExampleDTO exampleDTO = (ExampleDTO) target;
        if (exampleDTO.getExampleString() != null && exampleDTO.getExampleString().contains("unacceptable")) {
            errors.rejectValue("exampleString", "exampleString.unacceptable", "Example string contains unacceptable");
        }

        if (errors.hasErrors()) {
            log.info("Example request {} has unacceptable word: {}",
                exampleDTO,
                errors.getFieldErrors()
                    .stream()
                    .map(FieldError::getField)
                    .collect(Collectors.joining(", ")));
            return;
        }

        // TODO other checks
    }
}
