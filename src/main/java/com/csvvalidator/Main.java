package com.csvvalidator;

import com.csvvalidator.validators.*;
import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.ValidationResult;
import com.csvvalidator.validators.data.Violation;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    private static void printResults(List<Line> validRecords, List<ValidationResult> invalidRecords) {
        System.out.println("ValidRecords: ");
        validRecords.forEach(record -> System.out.println("\t" + record));
        System.out.println();

        System.out.println("InvalidRecords: ");
        invalidRecords.forEach(record -> {
            System.out.println("\t Line number:" + record.lineNumber());
            System.out.println("\t Line data: " + record.line());
            record.violations().forEach(violation -> {
                System.out.println("\t\t Violation: " + violation);
            });
            System.out.println();
        });
    }

    public static void main(String[] args) {

        // Parse the entire file and transform it into the list of lines
        final List<Line> lines = readFile("companies.csv");

        // Get the list of all validators
        final List<Validator> validators = getValidatorsList(lines);

        // Validate the complete file and return get the ValidationResults
        final List<ValidationResult> validationResults = IntStream.range(0, lines.size())
                .mapToObj(index -> validate(index + 2, lines.get(index), validators))
                .toList();

        // Extract correct records
        final List<Line> validRecords = validationResults.stream()
                .filter(ValidationResult::isValid)
                .map(ValidationResult::line)
                .toList();

        // Extract invalid records with all errors and line number
        final List<ValidationResult> invalidRecords = validationResults.stream()
                .filter(ValidationResult::isInvalid)
                .toList();

        // Print the results to the console
        printResults(validRecords, invalidRecords);
    }

    private static List<Validator> getValidatorsList(List<Line> lines) {
        return List.of(
                new IdValidator(),
                new CompanyValidator(),
                new ContactValidator(),
                new RfcValidator(lines),
                new EmailValidator(lines),
                new PhoneValidator(),
                new ForeignValidator()
        );
    }

    private static ValidationResult validate(final int lineNumber, final Line line, final List<Validator> validators) {
        final List<Violation> violations = validators.stream()
                .flatMap(validator -> validator.validate(line).stream())
                .toList();

        return new ValidationResult(lineNumber, line, violations);
    }

    @SneakyThrows
    private static List<Line> readFile(final String path) {
        return Files.readAllLines(Path.of("src/main/resources", path))
                .stream()
                .skip(1)
                .map(Line::toLine)
                .toList();
    }
}
