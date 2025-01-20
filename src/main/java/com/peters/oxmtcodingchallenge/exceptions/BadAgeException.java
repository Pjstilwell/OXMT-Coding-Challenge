package com.peters.oxmtcodingchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles incorrect age format in input. Expected format is
 * "<number> <time unit>"
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadAgeException extends RuntimeException {
    public BadAgeException(long machineID, String age) {
        super("Machine with ID: " + machineID + " has age in incorrect format. Age given: " + age + "\r\n"
                + "Correct format is \"<number> <time unit>\" where <time unit> is day/s, week/s, month/s, or year/s");
    }

}
