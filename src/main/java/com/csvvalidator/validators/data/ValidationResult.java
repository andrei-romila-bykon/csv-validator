package com.csvvalidator.validators.data;

import java.util.List;

public record ValidationResult(int lineNumber, Line line, List<Violation> violations) {

    /**
     * Indicates if the current line is valid
     *
     * @return True if its valid and false otherwise
     */
    public boolean isValid() {
        return violations.size() == 0;
    }
}
