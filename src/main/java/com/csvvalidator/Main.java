package com.csvvalidator;

import com.csvvalidator.validators.CompanyValidator;
import com.csvvalidator.validators.IdValidator;
import com.csvvalidator.validators.Validator;
import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.ValidationResult;
import com.csvvalidator.validators.data.Violation;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        final List<Line> lines = readFile("companies.csv");

        final List<Validator> validators = List.of(
                new IdValidator(),
                new CompanyValidator()
        );

        final List<ValidationResult> validationResults = IntStream.range(0, lines.size())
                .mapToObj(index -> validate(index + 2, lines.get(index), validators))
                .toList();

        System.out.println("validationResults = " + validationResults);
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
