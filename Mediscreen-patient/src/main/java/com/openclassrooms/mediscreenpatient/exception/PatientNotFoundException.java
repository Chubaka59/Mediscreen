package com.openclassrooms.mediscreenpatient.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(int id){
        super("Patient with id " + id + " could not be found.");
    }
}
