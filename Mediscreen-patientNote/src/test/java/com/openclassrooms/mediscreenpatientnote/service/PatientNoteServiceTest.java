package com.openclassrooms.mediscreenpatientnote.service;

import com.openclassrooms.mediscreenpatientnote.exception.NoteNotFoundException;
import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.repository.PatientNoteRepository;
import com.openclassrooms.mediscreenpatientnote.service.impl.PatientNoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PatientNoteServiceTest {
    @MockBean
    private PatientNoteService patientNoteService;
    private final PatientNoteRepository patientNoteRepository = mock(PatientNoteRepository.class);

    @BeforeEach
    public void setupPerTest() {
        patientNoteService = new PatientNoteServiceImpl(patientNoteRepository);
    }

    @Test
    public void getNoteListByPatientIdWhenPatientNoteExistTest(){
        //GIVEN we should get a list of note
        when(patientNoteRepository.findAllByPatientIdOrderByDateDesc(anyInt())).thenReturn(new ArrayList<>());

        //WHEN we call this method
        patientNoteService.getNoteListByPatientId(1);

        //THEN we get the correct list
        verify(patientNoteRepository, times(1)).findAllByPatientIdOrderByDateDesc(anyInt());
    }

    @Test
    public void addNoteToAPatientWhenPatientNoteAlreadyExistTest(){
        //GIVEN we should call the repository to add a note
        Note noteToAdd = new Note(null, null, null, "testNote");
        when(patientNoteRepository.save(any(Note.class))).thenReturn(noteToAdd);

        //WHEN we try to add a note
        Note actualNote = patientNoteService.addNoteToAPatient(noteToAdd, 1);

        //THEN a note is saved in db
        verify(patientNoteRepository, times(1)).save(any(Note.class));
        assertEquals(1, actualNote.getPatientId());
        assertNotNull(actualNote.getDate());
    }

    @Test
    public void getNoteByIdTest() {
        //GIVEN we should get a note
        when(patientNoteRepository.findById(anyString())).thenReturn(Optional.of(new Note()));

        //WHEN we call the method
        patientNoteService.getNoteById("test");

        //THEN the correct method is called
        verify(patientNoteRepository, times(1)).findById(anyString());
    }

    @Test
    public void getNoteByIdWhenNoteIsNotFoundTest() {
        //GIVEN we should not get a note
        when(patientNoteRepository.findById(anyString())).thenReturn(Optional.empty());

        //WHEN we call the method THEN an exception is thrown
        assertThrows(NoteNotFoundException.class, () -> patientNoteService.getNoteById("test"));
    }

    @Test
    public void updateNoteTest() {
        //GIVEN a note should be saved
        Note savedNote = new Note();
        when(patientNoteRepository.save(any(Note.class))).thenReturn(savedNote);
        when(patientNoteRepository.findById(anyString())).thenReturn(Optional.of(new Note()));

        //WHEN we call this method
        patientNoteService.updateNote("testId", "testNote");

        //THEN the note is saved
        verify(patientNoteRepository, times(1)).save(any(Note.class));
    }
}
