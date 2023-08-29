package com.openclassrooms.mediscreenpatientnote.controller;

import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.service.PatientNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientNoteController {
    @Autowired
    private final PatientNoteService patientNoteService;

    @GetMapping(value = "/patients/{id}/notes")
    public List<Note> getNoteListByPatientId(@PathVariable int id){
        return patientNoteService.getNoteListByPatientId(id);
    }

    @PostMapping(value = "/patients/{id}/notes")
    public ResponseEntity<Note> addNote(@RequestBody Note note, @PathVariable Integer id){
        patientNoteService.addNoteToAPatient(note, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/patients/{patientId}/notes/{noteId}")
    public Note getNoteById(@PathVariable("patientId") Integer patientId, @PathVariable("noteId") String noteId) {
        return patientNoteService.getNoteById(noteId);
    }

    @PutMapping(value = "/patients/{patientId}/notes/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable("patientId") Integer patientId, @PathVariable("noteId") String noteId, @RequestBody String note) {
        patientNoteService.updateNote(noteId, note);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
