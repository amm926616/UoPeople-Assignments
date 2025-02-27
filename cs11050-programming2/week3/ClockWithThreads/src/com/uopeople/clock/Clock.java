package com.uopeople.clock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Clock class is responsible for retrieving and formatting
 * the current time and date.
 */
public class Clock {
    private final SimpleDateFormat dateTimeFormat;

    public Clock() {
        // Initialize the date-time format
        this.dateTimeFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    }

    /**
     * Get the formatted current time and date.
     *
     * @return Formatted time and date as a String
     */
    public String getCurrentTime() {
        return dateTimeFormat.format(new Date());
    }
}
