package com.openclassrooms.mediscreenpatientnote.exception;

public class NoteNotFoundException extends NotFoundException {
    public NoteNotFoundException(String id){
        super("Note with id " + id + " could not be found.");
    }
}
