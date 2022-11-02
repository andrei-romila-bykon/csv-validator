package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class EmailValidatorTest {

    static Line getLine(String email) {
        return new Line(1, null, null, null, null, email, null, null);
    }

    @Test
    void validate_whenValidEmail_receiveEmptyOptional() {

        final Line line = getLine("john.doe@email.com.mx");

        final Optional<Violation> violation = new EmailValidator(List.of(line)).validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenNullEmail_receiveViolation() {

        final Line line = getLine(null);

        final Optional<Violation> violation = new EmailValidator(List.of()).validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The email address is required.");
    }

    @Test
    void validate_whenEmptyEmail_receiveViolation() {

        final Line line = getLine("");

        final Optional<Violation> violation = new EmailValidator(List.of()).validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The email address is required.");
    }

    @Test
    void validate_whenInvalidEmail_receiveViolation() {

        final Line line = getLine("invalid email address @ .com.mx");

        final Optional<Violation> violation = new EmailValidator(List.of()).validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The email address contains invalid characters.");
    }

    @Test
    void validate_whenDuplicatedEmail_receiveViolation() {
        final Line line1 = new Line(1, null, null, null, null, "john.doe@email.com", null, null);
        final Line line2 = new Line(2, null, null, null, null, "john.doe@email.com", null, null);

        final Optional<Violation> violation = new EmailValidator(List.of(line1, line2)).validate(line2);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The email address must be unique in the file.");
    }

    @Test
    void validateFirstLine_whenDuplicatedEmail_receiveEmptyOptional() {
        final Line line1 = new Line(1, null, null, null, null, "john.doe@email.com", null, null);
        final Line line2 = new Line(2, null, null, null, null, "john.doe@email.com", null, null);

        // The first line should NOT have any errors (there are no duplicates until we get to the second line)
        final Optional<Violation> violation = new EmailValidator(List.of(line1, line2)).validate(line1);

        assertThat(violation).isEmpty();
    }
}
