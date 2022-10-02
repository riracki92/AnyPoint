package com.anypoint.validation;

import com.anypoint.PriceRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class Validator {

    public static void validateRequest(PriceRequest request) {
        String exceptionMessage = null;
        if (request.getPrice() <= 0) {
            exceptionMessage = "Price value is invalid:" + request.getPrice();
        }
        if (request.getPriceModifier() < 0.9) {
            exceptionMessage = "Price modifier value is too low:" + request.getPriceModifier();
        } else if (request.getPriceModifier() > 1.02) {
            exceptionMessage = "Price modifier value is too high: " + request.getPriceModifier();
        }
        if (!isValidDatetime(request.getDatetime())) {
            exceptionMessage = "Datetime value is invalid: " + request.getDatetime();
        }
        if (StringUtils.isNotEmpty(exceptionMessage)) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private static boolean isValidDatetime(String datetime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = dateFormat.parse(datetime);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
