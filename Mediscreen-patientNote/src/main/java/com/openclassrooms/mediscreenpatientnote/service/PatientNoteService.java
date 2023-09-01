package com.openclassrooms.mediscreenpatientnote.service;

import com.openclassrooms.mediscreenpatientnote.model.Note;

import java.util.List;

public interface PatientNoteService {
    /**
     * get a note list from a patient
     * @param id the id of the patient
     * @return a list of note
     */
    List<Note> getNoteListByPatientId(int id);

    /**
     * add a note linked to a patient
     * @param note the note to add
     * @param id the id of the patient
     * @return the note that has been added
     */
    Note addNoteToAPatient(Note note, Integer id);

    /**
     * get a note by its id
     * @param id the id of the note
     * @return the note
     */
    Note getNoteById(String id);

    /**
     * update a note
     * @param id the id of the note to update
     * @param note the updated information of the note
     * @return the updated note
     */
    Note updateNote(String id, String note);
}
