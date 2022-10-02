package com.anypoint.util;

import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeFormatterTest {

    @Test
    public void shouldConvertDateTimeToCorrectFormat() {
        assertEquals(Date.from(Instant.ofEpochMilli(1641006000000L)), DateTimeFormatter.formatDateTime("2022-01-01T12:00:00Z"));
    }

    @Test
    public void shouldThrowExceptionWhenDateTimeIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> DateTimeFormatter.formatDateTime("ABC"));
        assertThrows(IllegalArgumentException.class,
                () -> DateTimeFormatter.formatDateTime("2022-01-01"));

    }
}
