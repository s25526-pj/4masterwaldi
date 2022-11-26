package com.lekarze.test4.exception;

public class PatientUnavailableException extends RuntimeException {

    public PatientUnavailableException(String errorMessage) {
        super(errorMessage);
    }

}
