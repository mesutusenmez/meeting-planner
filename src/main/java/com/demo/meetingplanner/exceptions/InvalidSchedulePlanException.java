package com.demo.meetingplanner.exceptions;

public class InvalidSchedulePlanException extends RuntimeException {

    public InvalidSchedulePlanException(String message) {
        super(message);
    }
}
