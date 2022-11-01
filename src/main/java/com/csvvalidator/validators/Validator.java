package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.Optional;

interface Validator {

    Optional<Violation> validate(final Line line);

    default boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    default Optional<Violation> getViolation(final String field, final String message) {
        return Optional.of(new Violation(field, message));
    }
}
