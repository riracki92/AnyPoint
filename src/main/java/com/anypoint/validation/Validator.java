package com.anypoint.validation;

import com.anypoint.PriceRequest;
import com.anypoint.SalesRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Validator {

    public static void validatePriceRequest(PriceRequest request) {
        String exceptionMessage = null;
        if (request.getPrice() <= 0) {
            exceptionMessage = "Price value is invalid:" + request.getPrice();
        }
        if (request.getPriceModifier() < 0.9) {
            exceptionMessage = "Price modifier value is too low:" + request.getPriceModifier();
        } else if (request.getPriceModifier() > 1.02) {
            exceptionMessage = "Price modifier value is too high: " + request.getPriceModifier();
        }
        if (isInvalidDatetime(request.getDatetime())) {
            exceptionMessage = "Datetime value is invalid: " + request.getDatetime();
        }
        if (StringUtils.isNotEmpty(request.getAdditionalItem().getLast4())) {
            if (request.getAdditionalItem().getLast4().length() != 4) {
                exceptionMessage = "Last4 length is invalid: " + request.getAdditionalItem().getLast4();
            } else if (!NumberUtils.isParsable(request.getAdditionalItem().getLast4())) {
                exceptionMessage = "Last4 is not a numeric value: " + request.getAdditionalItem().getLast4();
            }
        }
        if (StringUtils.isNotEmpty(request.getAdditionalItem().getBankName())) {
            if (request.getAdditionalItem().getBankName().length() < 3
                    || request.getAdditionalItem().getBankName().length() > 20) {
                exceptionMessage = "Bank name length is invalid: " + request.getAdditionalItem().getBankName();
            }
        }
        if (StringUtils.isNotEmpty(request.getAdditionalItem().getBankAccountNumber())) {
            if (request.getAdditionalItem().getBankAccountNumber().length() != 16) {
                exceptionMessage = "Bank account number length is invalid: " + request.getAdditionalItem().getBankAccountNumber();
            } else if (!NumberUtils.isParsable(request.getAdditionalItem().getBankAccountNumber())) {
                exceptionMessage = "Bank account number is not a numeric value: " + request.getAdditionalItem().getBankAccountNumber();
            }
        }
        if (StringUtils.isNotEmpty(request.getAdditionalItem().getChequeNumber())) {
            if (request.getAdditionalItem().getChequeNumber().length() != 16) {
                exceptionMessage = "Cheque number length is invalid: " + request.getAdditionalItem().getChequeNumber();
            } else if (!NumberUtils.isParsable(request.getAdditionalItem().getChequeNumber())) {
                exceptionMessage = "Cheque number is not a numeric value: " + request.getAdditionalItem().getChequeNumber();
            }
        }
        if (StringUtils.isNotEmpty(exceptionMessage)) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void validateSalesRequest(SalesRequest request) {
        String exceptionMessage = null;
        if (isInvalidDatetime(request.getStartDateTime())) {
            exceptionMessage = "Start Datetime value is invalid: " + request.getStartDateTime();
        }
        if (isInvalidDatetime(request.getEndDateTime())) {
            exceptionMessage = "End Datetime value is invalid: " + request.getEndDateTime();
        }
        if (StringUtils.isNotEmpty(exceptionMessage)) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private static boolean isInvalidDatetime(String datetime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            dateFormat.parse(datetime);
        } catch (ParseException e) {
            return true;
        }
        return false;
    }
}
