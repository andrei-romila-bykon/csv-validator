package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class IdValidatorTest {

    @Test
    void validate_whenNullId_receiveViolation() {

        Line line = new Line(null, null, null, null, null, null);

        Optional<Violation> violation = new IdValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The id is required.");
    }

    @Test
    void validate_whenEmptyId_receiveViolation() {

        Line line = new Line("", null, null, null, null, null);

        Optional<Violation> violation = new IdValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The id is required.");
    }

    @Test
    void validate_whenSpacesId_receiveViolation() {

        Line line = new Line("      ", null, null, null, null, null);

        Optional<Violation> violation = new IdValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The id is required.");
    }

    @Test
    void validate_whenNotNumericId_receiveViolation() {

        Line line = new Line("not-a-number", null, null, null, null, null);

        Optional<Violation> violation = new IdValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The id must be a number greater than 0.");
    }

    @Test
    void validate_whenNumberLessThanZeroId_receiveViolation() {

        Line line = new Line("-1", null, null, null, null, null);

        Optional<Violation> violation = new IdValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The id must be a number greater than 0.");
    }

    @Test
    void validate_whenValidInput_receiveEmptyOptional() {

        Line line = new Line("1", null, null, null, null, null);

        Optional<Violation> violation = new IdValidator().validate(line);

        assertThat(violation).isEmpty();
    }
}
