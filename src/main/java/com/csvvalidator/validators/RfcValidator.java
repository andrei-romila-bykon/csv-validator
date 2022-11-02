package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

class RfcValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^[A-Z0-9]{13}$");

    private List<Line> lines;

    public RfcValidator(final List<Line> lines) {
        this.lines = lines;
    }

    private Optional<Violation> getViolation(final String message) {
        return getViolation("rfc", message);
    }

    @Override
    public Optional<Violation> validate(Line line) {

        final String value = line.rfc();

        if (isEmpty(value)) {
            return getViolation("The RFC is required.");
        }

        if ( ! isValid(value)) {
            return getViolation("The RFC must contain only 13 letters and numbers.");
        }

        if (isDuplicated(line, value)) {
            return getViolation("The RFC must be unique in the file.");
        }

        return Optional.empty();
    }

    private boolean isDuplicated(final Line line, final String value) {
        return lines.stream()
                .filter(it -> line.number() > it.number())
                .anyMatch(it -> value.equalsIgnoreCase(it.rfc()));
    }

    private boolean isValid(String value) {
        return pattern.matcher(value).matches();
    }
}
