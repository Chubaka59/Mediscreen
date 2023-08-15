package com.openclassrooms.mediscreenpatientnote.service;

import com.openclassrooms.mediscreenpatientnote.model.Note;

import java.util.List;

public interface PatientNoteService {
    List<Note> getNoteListByPatientId(int id);
    void addNoteToAPatient(Note note, Integer id);
}
