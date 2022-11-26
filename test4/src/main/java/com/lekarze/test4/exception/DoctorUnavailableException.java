package com.lekarze.test4.exception;

public class DoctorUnavailableException extends RuntimeException {

    public DoctorUnavailableException(String errorMessage) {
        super(errorMessage);
    }
}
