package com.openclassrooms.mediscreenpatient.exception;

public class PatientAlreadyExistException extends ConflictException {
    public PatientAlreadyExistException(String firstName, String lastName){
        super("Patient " + firstName + " " + lastName + " already exist");
    }
}
