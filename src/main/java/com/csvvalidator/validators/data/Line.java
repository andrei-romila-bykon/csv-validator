package com.csvvalidator.validators.data;

public record Line(String id, String company, String contact, String rfc, String email, String foreign) {

    public static Line toLine(final String value) {

        // Split the line into fields
        final String[] fields = value.split(",");

        // Here we can create a valid line
        return new Line(get(fields, 0),
                get(fields, 1),
                get(fields, 2),
                get(fields, 3),
                get(fields, 4),
                get(fields, 5));
    }

    /**
     * Simple method to avoid {@link NullPointerException} if there
     * is a line with less than 6 fields
     *
     * @param fields {@link String}[] The list of fields
     * @param index  {@link Integer} The index to access
     * @return The value at the provided index or null if there is no index
     */
    private static String get(final String[] fields, final int index) {
        if (index < fields.length) {
            return fields[index];
        }

        return null;
    }
}
