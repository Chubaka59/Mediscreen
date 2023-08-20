package com.openclassrooms.mediscreenpatientnote.controller;

import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.service.PatientNoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

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
        doNothing().when(patientNoteService).addNoteToAPatient(any(Note.class), anyInt());

        //WHEN we call this method
        ResponseEntity<Note> actualResponse = patientNoteController.addNote(new Note("test"), 1);

        //THEN we get the correct response
        assertEquals(HttpStatusCode.valueOf(201), actualResponse.getStatusCode());
        verify(patientNoteService, times(1)).addNoteToAPatient(any(Note.class), anyInt());

    }
}
