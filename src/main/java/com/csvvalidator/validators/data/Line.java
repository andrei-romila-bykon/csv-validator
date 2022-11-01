package com.csvvalidator.validators.data;

public record Line(String id, String company, String contact, String rfc, String email, String foreign) {

    public static Line toLine(String value) {
        String[] fields = value.split(",");

        // We MUST have 6 fields or the entire line is invalid
        if (fields.length != 6) {
            throw new IllegalStateException("The file is corrupted.");
        }

        // Here we can create a valid line
        return new Line(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
    }
}
