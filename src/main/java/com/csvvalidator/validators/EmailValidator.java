package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.List;
import java.util.Optional;

class EmailValidator implements Validator {

    private final List<Line> lines;

    public EmailValidator(final List<Line> lines) {
        this.lines = lines;
    }

    private Optional<Violation> getViolation(final String message) {
        return getViolation("email", message);
    }

    @Override
    public Optional<Violation> validate(final Line line) {

        final String value = line.email();

        if (isEmpty(value)) {
            return getViolation("The email address is required.");
        }

        if (isDuplicated(line, value)) {
            return getViolation("The email address must be unique in the file.");
        }

        return Optional.empty();
    }

    /**
     * Indicates if the email is repeated in the current file
     * but only if the email is second in the file so the first
     * one doesn't get any error messages.
     *
     * @param value {@link String} The email value under test
     * @return True if its duplicated and false otherwise
     */
    private boolean isDuplicated(final Line line, final String value) {
        return lines.stream()
                // Checking the line number to make sure
                // we do not count the current value
                .filter(it -> line.number() > it.number())
                .anyMatch(it -> value.equalsIgnoreCase(it.email()));
    }
}
