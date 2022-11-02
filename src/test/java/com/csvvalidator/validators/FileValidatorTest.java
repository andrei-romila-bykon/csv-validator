package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.ValidationResult;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class FileValidatorTest {

    @Test
    void fileValidator_whenInvalidFilePath_throwsIOException() {
        assertThrows(IOException.class, () -> new FileValidator("unknown_file.csv"));
    }

    @Test
    void getValidRecords() {
        FileValidator validator = new FileValidator("src/test/resources/companies_errors.csv");

        List<Line> validRecords = validator.getValidRecords();

        assertThat(validRecords).hasSize(1);
    }

    @Test
    void getInvalidRecords() {
        FileValidator validator = new FileValidator("src/test/resources/companies_errors.csv");

        List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        assertThat(invalidRecords).hasSize(1);
        assertThat(invalidRecords.get(0).line().number()).isEqualTo(2);
    }

    @Test
    void getInvalidRecords_duplicatedEmails() {
        FileValidator validator = new FileValidator("src/test/resources/emails_duplicates.csv");

        List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        assertThat(invalidRecords).hasSize(2);
        assertThat(invalidRecords.get(0).line().number()).isEqualTo(2);
    }

    @Test
    void getInvalidRecords_duplicatedRfcs() {
        FileValidator validator = new FileValidator("src/test/resources/rfcs_duplicates.csv");

        List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        assertThat(invalidRecords).hasSize(2);
        assertThat(invalidRecords.get(0).line().number()).isEqualTo(2);
    }

}
