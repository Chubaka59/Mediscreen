package com.openclassrooms.mediscreenpatientnote.service.impl;

import com.openclassrooms.mediscreenpatientnote.exception.NoteNotFoundException;
import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.repository.PatientNoteRepository;
import com.openclassrooms.mediscreenpatientnote.service.PatientNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientNoteServiceImpl implements PatientNoteService {
    @Autowired
    private final PatientNoteRepository patientNoteRepository;

    public List<Note> getNoteListByPatientId(int id){
        return patientNoteRepository.findAllByPatientIdOrderByDateDesc(id);
    }

    public Note addNoteToAPatient(Note note, Integer id){
        note.setPatientId(id);
        note.setDate(LocalDateTime.now());
        return patientNoteRepository.save(note);
    }

    public Note getNoteById(String id) {
        Note note = patientNoteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        return note;
    }

    public Note updateNote(String id, String note) {
        Note updatedNote = getNoteById(id).update(note);
        return patientNoteRepository.save(updatedNote);
    }
}
