package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.List;
import java.util.Optional;

public class RfcValidator implements Validator {

    private List<Line> lines;

    public RfcValidator(final List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public Optional<Violation> validate(Line line) {
        return Optional.empty();
    }
}
