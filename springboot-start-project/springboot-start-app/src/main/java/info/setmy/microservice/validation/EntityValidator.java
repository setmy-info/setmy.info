package info.setmy.microservice.validation;

import info.setmy.microservice.domain.ExampleModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class EntityValidator {

    private final Validator validator;

    public void validate(ExampleModel model) {
        Set<ConstraintViolation<ExampleModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
