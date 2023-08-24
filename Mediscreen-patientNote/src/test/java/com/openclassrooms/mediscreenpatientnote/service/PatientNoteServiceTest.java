package com.openclassrooms.mediscreenpatientnote.service;

import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.repository.PatientNoteRepository;
import com.openclassrooms.mediscreenpatientnote.service.impl.PatientNoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        PatientNote existingPatientNote = new PatientNote("1",1, new ArrayList<>());
        existingPatientNote.getNoteList().add(new Note("testNote"));
        when(patientNoteRepository.findByPatientId(anyInt())).thenReturn(Optional.of(existingPatientNote));

        //WHEN we call this method
        List<Note> actualNoteList = patientNoteService.getNoteListByPatientId(1);

        //THEN we get the correct list
        verify(patientNoteRepository, times(1)).findByPatientId(anyInt());
        assertEquals(existingPatientNote.getNoteList(), actualNoteList);
    }

    @Test
    public void getNoteListByPatientIdWhenPatientDoesNotExistTest(){
        //GIVEN we should get a list of note
        when(patientNoteRepository.findByPatientId(anyInt())).thenReturn(Optional.empty());

        //WHEN we call this method
        List<Note> actualNoteList = patientNoteService.getNoteListByPatientId(1);

        //THEN we get the correct list
        verify(patientNoteRepository, times(1)).findByPatientId(anyInt());
        assertEquals(new ArrayList<>(), actualNoteList);
    }

    @Test
    public void addNoteToAPatientWhenPatientNoteAlreadyExistTest(){
        //GIVEN we should call the repository to add a note
        PatientNote existingPatientNote = new PatientNote("1", 1, new ArrayList<>());
        when(patientNoteRepository.findByPatientId(anyInt())).thenReturn(Optional.of(existingPatientNote));
        when(patientNoteRepository.save(any(PatientNote.class))).thenReturn(existingPatientNote);

        //WHEN we try to add a note
        patientNoteService.addNoteToAPatient(new Note("testNoteToAdd"), 1);

        //THEN a note is saved in db
        verify(patientNoteRepository, times(1)).save(any(PatientNote.class));
    }

    @Test
    public void addNoteToAPatientWhenPatientDoesNotExistTest(){
        //GIVEN we should call the repository to add a note
        PatientNote patientNoteToCreate = new PatientNote("1", 1, new ArrayList<>());
        when(patientNoteRepository.findByPatientId(anyInt())).thenReturn(Optional.empty());
        when(patientNoteRepository.save(any(PatientNote.class))).thenReturn(patientNoteToCreate);

        //WHEN we try to add a note
        patientNoteService.addNoteToAPatient(new Note("testNoteToAdd"), 1);

        //THEN a note is created in db then the new note is saved
        verify(patientNoteRepository, times(2)).save(any(PatientNote.class));
    }
}
