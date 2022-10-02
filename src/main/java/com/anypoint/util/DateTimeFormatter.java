package com.anypoint.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {

    public static Date formatDateTime(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            return dateFormat.parse(dateTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("DateTime value is invalid: " + dateTime);
        }
    }
}
