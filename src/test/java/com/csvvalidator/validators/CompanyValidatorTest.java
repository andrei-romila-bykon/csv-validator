package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CompanyValidatorTest {

    @Test
    void validate_whenNullCompany_receiveViolation() {

        Line line = new Line(null, null, null, null, null, null);

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name is required.");
    }

    @Test
    void validate_whenSpacesCompany_receiveViolation() {

        Line line = new Line(null, "      ", null, null, null, null);

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name is required.");
    }

    @Test
    void validate_whenCompanyLengthGreaterThan50_receiveViolation() {
        String company51 = IntStream.range(0, 51).mapToObj(i -> "a").collect(Collectors.joining());
        Line line = new Line(null, company51, null, null, null, null);

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name must be at least 5 characters long and no longer than 50.");

    }

    @Test
    void validate_whenInvalidValueCompany_receiveViolation() {

        Line line = new Line(null, "+?_.", null, null, null, null);

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name must contain only letters, numbers and spaces.");
    }

    @Test
    void validate_whenValidInput_receiveEmptyOptional() {
        Line line = new Line(null, "Coca Cola", null, null, null, null);

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isEmpty();
    }
}
