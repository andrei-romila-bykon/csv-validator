package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.List;
import java.util.Optional;

public class EmailValidator implements Validator {

    private final List<Line> lines;

    public EmailValidator(final List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public Optional<Violation> validate(Line line) {
        return Optional.empty();
    }
}
