package com.peters.oxmtcodingchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles unhandled time unit in data. Time Unit should be
 * day/s, week/s, month/s, or year/s
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadTimeUnitException extends RuntimeException {
    public BadTimeUnitException(long machineID, String ageTimeUnit) {
        super("Machine with ID: " + machineID + " has an unhandled time unit of: " + ageTimeUnit);
    }
}
