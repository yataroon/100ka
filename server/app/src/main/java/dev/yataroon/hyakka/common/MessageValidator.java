package dev.yataroon.hyakka.common;

import java.util.Set;

import dev.yataroon.hyakka.common.model.ValidationResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@ApplicationScoped
public class MessageValidator {

    /**
     * * Jakarta Bean Validator
     */
    @Inject
    private Validator validator;

    /**
     * * 検証を実行
     * 
     * @param <T>
     * @param message
     * @return
     */
    public <T> ValidationResult validate(T message) {
        Set<ConstraintViolation<T>> violations = validator.validate(message);
        return new ValidationResult(violations.isEmpty(), violations);
    }
}
