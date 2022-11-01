package com.csvvalidator.validators.data;

import java.util.List;

public record ValidationResult(Line line, List<Violation> violations) {

    /**
     * Indicates if the current line is valid
     *
     * @return True if its valid and false otherwise
     */
    public boolean isValid() {
        return violations.size() == 0;
    }

    /**
     * Indicates if the current line is invalid
     *
     * @return True if its invalid and false otherwise
     */
    public boolean isInvalid() {
        return ! isValid();
    }
}
