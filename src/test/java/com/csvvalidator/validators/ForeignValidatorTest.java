package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ForeignValidatorTest {

    private static Line getLine(String foreign) {
        return new Line(1, null, null, null, null, null, null, foreign);
    }

    @Test
    void validate_whenValidForeign_receiveEmptyOptional() {

        final Line line = getLine("1");

        final Optional<Violation> violation = new ForeignValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenNullForeign_receiveViolation() {

        Line line = getLine(null);

        Optional<Violation> violation = new ForeignValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The foreign field is required.");
    }

    @Test
    void validate_whenEmptyForeign_receiveViolation() {

        Line line = getLine("");

        Optional<Violation> violation = new ForeignValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The foreign field is required.");
    }

    @Test
    void validate_whenInvalidForeign_receiveViolation() {

        final Line line = getLine("invalid-value");

        final Optional<Violation> violation = new ForeignValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The foreign field can contain only: 0, false, 1 or true.");
    }
}