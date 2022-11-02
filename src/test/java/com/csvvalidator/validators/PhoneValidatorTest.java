package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    private static Line getLine(String phone) {
        return new Line(1, null, null, null, null, null, phone, null);
    }

    @Test
    void validate_whenNullPhone_receiveEmptyOptional() {

        final Line line = getLine(null);

        final Optional<Violation> violation = new PhoneValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenEmptyPhone_receiveEmptyOptional() {

        final Line line = getLine("");

        final Optional<Violation> violation = new PhoneValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenInvalidPhone_receiveViolation() {

        final Line line = getLine("invalid-phone");

        final Optional<Violation> violation = new PhoneValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The phone number must contains 9 numbers.");
        assertThat(violation.get().field()).isEqualTo("phone");
    }
}
