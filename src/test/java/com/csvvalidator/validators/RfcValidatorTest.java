package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RfcValidatorTest {

    private static Line getLine(String rfc) {
        return new Line(1, null, null, null, rfc, null, null, null);
    }

    @Test
    void validate_whenNullRfc_receiveViolation() {

        final Line line = getLine(null);

        final Optional<Violation> violation = new RfcValidator(List.of(line)).validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The RFC is required.");
    }

    @Test
    void validate_whenEmptyRfc_receiveViolation() {

        final Line line = getLine("");

        final Optional<Violation> violation = new RfcValidator(List.of(line)).validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The RFC is required.");
    }

    @Test
    void validate_whenInvalidRfc_receiveViolation() {

        final Line line = getLine("invalid-rfc");

        final Optional<Violation> violation = new RfcValidator(List.of(line)).validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The RFC must contain only 13 letters and numbers.");
    }

    @Test
    void validate_whenDuplicatedRfc_receiveViolation() {

        final Line line1 = new Line(1, null, null, null, "RFC1234567890", null, null, null);
        final Line line2 = new Line(2, null, null, null, "RFC1234567890", null, null, null);

        final Optional<Violation> violation = new RfcValidator(List.of(line1, line2)).validate(line2);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The RFC must be unique in the file.");
    }
}