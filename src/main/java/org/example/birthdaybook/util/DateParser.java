package org.example.birthdaybook.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateParser {

    public static LocalDate stringToDate(String dateString) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Incorrect date format");
        }
    }
}
