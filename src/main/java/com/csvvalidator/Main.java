package com.csvvalidator;

import com.csvvalidator.validators.FileValidator;
import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.ValidationResult;

import java.util.List;

public class Main {

    private static void printResults(List<Line> validRecords, List<ValidationResult> invalidRecords) {
        System.out.println("ValidRecords: ");
        validRecords.forEach(record -> System.out.println("\t" + record));
        System.out.println();

        System.out.println("InvalidRecords: ");
        invalidRecords.forEach(record -> {
            System.out.println("\t Line data: " + record.line());
            record.violations().forEach(violation -> {
                System.out.println("\t\t Violation: " + violation);
            });
            System.out.println();
        });
    }

    public static void main(String[] args) {

        FileValidator validator = new FileValidator("src/main/resources/companies.csv");

        // Extract correct records
        final List<Line> validRecords = validator.getValidRecords();

        // Extract invalid records with all errors and line number
        final List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        // Print the results to the console
        printResults(validRecords, invalidRecords);
    }

}
