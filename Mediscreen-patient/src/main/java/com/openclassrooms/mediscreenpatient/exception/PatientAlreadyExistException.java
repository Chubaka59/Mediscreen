package com.openclassrooms.mediscreenpatient.exception;

public class PatientAlreadyExistException extends RuntimeException {
    public PatientAlreadyExistException(String firstName, String lastName){
        super("Patient " + firstName + " " + lastName + " already exist");
    }
}
