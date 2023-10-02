package com.demo.meetingplanner.exceptions;

public class EmptyEventException extends RuntimeException {

    public EmptyEventException(String message) {
        super(message);
    }
}
