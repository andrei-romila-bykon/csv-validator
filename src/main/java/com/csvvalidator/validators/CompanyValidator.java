package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.Optional;
import java.util.regex.Pattern;

public class CompanyValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^[a-z0-9 ]+$", Pattern.CASE_INSENSITIVE);

    private Optional<Violation> getViolation(final String message) {
        return getViolation("company", message);
    }

    @Override
    public Optional<Violation> validate(final Line line) {

        final String value = line.company();

        if (isEmpty(value)) {
            return getViolation("The company name is required.");
        }

        if ( ! matchPattern(value)) {
            return getViolation("The company name must contain only letters, numbers and spaces.");
        }

        if (invalidLength(value)) {
            return getViolation("The company name must be at least 5 characters long and no longer than 50.");
        }

        return Optional.empty();
    }

    /**
     * The company name must have at least 5 characters and no more than 50
     *
     * @param value {@link String} The value under test
     * @return True if invalid false otherwise
     */
    private boolean invalidLength(final String value) {
        return value.length() < 5 || value.length() > 50;
    }

    /**
     * Indicates if the company contains only allowed characters
     *
     * @param value {@link String} The value under test
     * @return True if matches the pattern false otherwise
     */
    private boolean matchPattern(final String value) {
        return pattern.matcher(value).matches();
    }
}
