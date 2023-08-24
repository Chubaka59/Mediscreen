package com.openclassrooms.mediscreenpatientnote.service.impl;

import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.repository.PatientNoteRepository;
import com.openclassrooms.mediscreenpatientnote.service.PatientNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientNoteServiceImpl implements PatientNoteService {
    @Autowired
    private final PatientNoteRepository patientNoteRepository;

    public List<Note> getNoteListByPatientId(int id){
        return patientNoteRepository.findAllByPatientIdOrderByDateDesc(id);
    }

    public void addNoteToAPatient(Note note, Integer id){
        note.setPatientId(id);
        note.setDate(LocalDateTime.now());
        patientNoteRepository.save(note);
    }
}
