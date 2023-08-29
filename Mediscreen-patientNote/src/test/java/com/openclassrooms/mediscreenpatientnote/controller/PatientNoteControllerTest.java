package com.openclassrooms.mediscreenpatientnote.controller;

import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.service.PatientNoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PatientNoteControllerTest {
    @MockBean
    private PatientNoteController patientNoteController;
    private PatientNoteService patientNoteService = mock(PatientNoteService.class);

    @BeforeEach
    public void setupPerTest() {
        patientNoteController = new PatientNoteController(patientNoteService);
    }

    @Test
    public void getNoteListByPatientIdTest() {
        //GIVEN we should get a list of note
        when(patientNoteService.getNoteListByPatientId(anyInt())).thenReturn(new ArrayList<>());

        //WHEN we call the method
        patientNoteController.getNoteListByPatientId(1);

        //THEN the correct method is called
        verify(patientNoteService, times(1)).getNoteListByPatientId(anyInt());
    }

    @Test
    public void addNoteTest(){
        //GIVEN a note will be added
        when(patientNoteService.addNoteToAPatient(any(Note.class), anyInt())).thenReturn(new Note());

        //WHEN we call this method
        ResponseEntity<Note> actualResponse = patientNoteController.addNote(new Note(null, null, LocalDateTime.now(), "test"), 1);

        //THEN we get the correct response
        assertEquals(HttpStatusCode.valueOf(201), actualResponse.getStatusCode());
        verify(patientNoteService, times(1)).addNoteToAPatient(any(Note.class), anyInt());
    }

    @Test
    public void getNoteByIdTest() {
        //GIVEN we should call this method
        when(patientNoteService.getNoteById(anyString())).thenReturn(new Note());

        //WHEN we call the method
        patientNoteController.getNoteById(1, "test");

        //THEN the correct method is called
        verify(patientNoteService, times(1)).getNoteById(anyString());
    }

    @Test
    public void updateNoteTest() {
        //GIVEN we should get this response
        ResponseEntity<Note> expectedResponse = new ResponseEntity<>(HttpStatusCode.valueOf(200));
        when(patientNoteService.updateNote(anyString(), anyString())).thenReturn(new Note());

        //WHEN we call the method
        ResponseEntity<Note> actualResponse = patientNoteController.updateNote(1, "testId", "testnote");

        //THEN the correct method is called and we get the correct response
        assertEquals(expectedResponse, actualResponse);
        verify(patientNoteService, times(1)).updateNote(anyString(), anyString());
    }
}
