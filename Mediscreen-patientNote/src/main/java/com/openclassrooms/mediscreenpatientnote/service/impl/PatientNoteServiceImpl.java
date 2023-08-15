package com.openclassrooms.mediscreenpatientnote.service.impl;

import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.model.PatientNote;
import com.openclassrooms.mediscreenpatientnote.repository.PatientNoteRepository;
import com.openclassrooms.mediscreenpatientnote.service.PatientNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientNoteServiceImpl implements PatientNoteService {
    @Autowired
    private PatientNoteRepository patientNoteRepository;

    public List<Note> getNoteListByPatientId(int id){
        Optional<PatientNote> existingPatientNote = patientNoteRepository.findByPatientId(id);
        if (existingPatientNote.isEmpty()) {
            PatientNote patientNote = new PatientNote();
            patientNote.setPatientId(id);
            patientNote.setNoteList(new ArrayList<>());
            patientNoteRepository.save(patientNote);
            return new ArrayList<>();
        } else {
            return existingPatientNote.get().getNoteList();
        }
    }

    public void addNoteToAPatient(Note note, Integer id){
        PatientNote patientNote = patientNoteRepository.findByPatientId(id).orElseThrow();
        patientNote.getNoteList().add(note);
        patientNoteRepository.save(patientNote);
    }
}
