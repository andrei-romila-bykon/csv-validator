package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.Optional;
import java.util.regex.Pattern;

public class CompanyValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^[a-z0-9 ]+$", Pattern.CASE_INSENSITIVE);

    @Override
    public Optional<Violation> validate(final Line line) {

        final String value = line.company();

        if (isEmpty(value)) {
            return getViolation("company", "The company name is required.");
        }

        if ( ! isValid(value)) {
            return getViolation("company", "The company name must contain only letters, numbers and spaces.");
        }

        if (value.length() < 5 || value.length() > 50) {
            return getViolation("company", "The company name must be at least 5 characters long and no longer than 50.");
        }

        return Optional.empty();
    }

    private boolean isValid(final String value) {
        return pattern.matcher(value).matches();
    }
}
