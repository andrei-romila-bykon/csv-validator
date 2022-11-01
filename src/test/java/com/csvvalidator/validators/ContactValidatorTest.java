package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContactValidatorTest {

    private static Line getLine(String contact) {
        return new Line(1, null, null, contact, null, null, null, null);
    }

    @Test
    void validate_whenValidContact_receiveEmptyOptional() {

        // The contact is not required
        Line line = getLine("Coca Cola");

        Optional<Violation> violation = new ContactValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenNullContact_receiveEmptyOptional() {

        // The contact is not required
        Line line = getLine(null);

        Optional<Violation> violation = new ContactValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenEmptyContact_receiveEmptyOptional() {

        // The contact is not required
        Line line = getLine("");

        Optional<Violation> violation = new ContactValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenInvalidContact_receiveViolation() {

        Line line = getLine("-invalid-contact-");

        Optional<Violation> violation = new ContactValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The contact name must contain only letters, numbers and spaces.");
    }

    @Test
    void validate_whenLengthLessThan5Contact_receiveViolation() {

        Line line = getLine("abc");

        Optional<Violation> violation = new ContactValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The contact name must be at least 5 characters long and no longer than 50.");
    }

    @Test
    void validate_whenLengthGreaterThan50Contact_receiveViolation() {

        String contact51 = IntStream.range(0, 51).mapToObj(i -> "a").collect(Collectors.joining());
        Line line = getLine(contact51);

        Optional<Violation> violation = new ContactValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The contact name must be at least 5 characters long and no longer than 50.");
    }
}
