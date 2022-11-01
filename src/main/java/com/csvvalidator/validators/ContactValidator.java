package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.Optional;
import java.util.regex.Pattern;

public class ContactValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^[a-z0-9 ]+$", Pattern.CASE_INSENSITIVE);

    private Optional<Violation> getViolation(final String message) {
        return getViolation("contact", message);
    }

    @Override
    public Optional<Violation> validate(Line line) {

        // Get the contact from the line
        final String value = line.contact();

        // If there is no value it's ok
        if (value == null || value.trim().isEmpty()) {
            return Optional.empty();
        }

        if ( ! isValid(value)) {
            return getViolation("The contact name must contain only letters, numbers and spaces.");
        }

        // The length of the value must be between 5 and 50 characters
        if (invalidLength(value)) {
            return getViolation("The contact name must be at least 5 characters long and no longer than 50.");
        }

        return Optional.empty();
    }

    private boolean isValid(String value) {
        return pattern.matcher(value).matches();
    }

    /**
     * The value must have at least 5 characters and no more than 50
     *
     * @param value {@link String} The value under test
     * @return True if invalid false otherwise
     */
    private boolean invalidLength(final String value) {
        return value.length() < 5 || value.length() > 50;
    }
}
