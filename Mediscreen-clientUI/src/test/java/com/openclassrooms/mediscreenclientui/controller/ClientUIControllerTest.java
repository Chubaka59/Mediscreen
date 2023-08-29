package com.openclassrooms.mediscreenclientui.controller;

import com.openclassrooms.mediscreenclientui.bean.NoteBean;
import com.openclassrooms.mediscreenclientui.bean.PatientBean;
import com.openclassrooms.mediscreenclientui.proxy.PatientNoteProxy;
import com.openclassrooms.mediscreenclientui.proxy.PatientProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ClientUIControllerTest {
    @InjectMocks
    ClientUIController clientUIController;
    @Mock
    PatientProxy patientProxy;
    @Mock
    PatientNoteProxy patientNoteProxy;
    @Mock
    Model model;
    @Mock
    BindingResult result;

    @Test
    public void getAllPatientsTest(){
        //GIVEN we should get this String
        String expectedString = "patientListPage";

        //WHEN we call this method
        String actualString = clientUIController.getAllPatients(model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void showAddPageTest(){
        //GIVEN we should get this String
        String expectedString = "addPatientPage";
        PatientBean patient = new PatientBean();

        //WHEN we call this method
        String actualString = clientUIController.showAddPage(patient);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void addPatientTest(){
        //GIVEN we should get this String
        String expectedString = "patientListPage";
        PatientBean patient = new PatientBean();
        ResponseEntity<PatientBean> response = new ResponseEntity<>(HttpStatusCode.valueOf(201));
        when(patientProxy.addPatient(any(PatientBean.class))).thenReturn(response);

        //WHEN we call the method
        String actualString = clientUIController.addPatient(patient, result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void addPatientWhenFormHasErrorTest(){
        //GIVEN there is an error in the form
        String expectedString = "addPatientPage";
        when(result.hasErrors()).thenReturn(true);
        PatientBean patient = new PatientBean();

        //WHEN we call the method
        String actualString = clientUIController.addPatient(patient, result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void addPatientWhenPatientIsNotCreatedTest(){
        //GIVEN the patient we would create is not
        String expectedString = "addPatientPage";
        PatientBean patient = new PatientBean();
        ResponseEntity<PatientBean> response = new ResponseEntity<>(HttpStatusCode.valueOf(409));
        when(patientProxy.addPatient(any(PatientBean.class))).thenReturn(response);

        //WHEN we call the method
        String actualString = clientUIController.addPatient(patient, result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void showUpdatePageTest(){
        //GIVEN we should get this String
        String expectedString = "updatePatientPage";

        //WHEN we call the method
        String actualString = clientUIController.showUpdatePage(1, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void updatePatientTest(){
        //GIVEN we should get this String
        String expectedString = "patientListPage";
        PatientBean patient = new PatientBean();
        ResponseEntity<PatientBean> response = new ResponseEntity<>(HttpStatusCode.valueOf(200));
        when(patientProxy.updatePatient(any(PatientBean.class), anyInt())).thenReturn(response);

        //WHEN we call the method
        String actualString = clientUIController.updatePatient(1, patient, result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void updatePatientWhenFormHasError(){
        //GIVEN there is an error in the form
        String expectedString = "updatePatientPage";
        when(result.hasErrors()).thenReturn(true);
        PatientBean patient = new PatientBean();

        //WHEN we call the method
        String actualString = clientUIController.updatePatient(1, patient, result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void updatePatientWhenPatientIsNotUpdatedTest(){
        //GIVEN the patient we would create is not
        String expectedString = "updatePatientPage";
        PatientBean patient = new PatientBean();
        ResponseEntity<PatientBean> response = new ResponseEntity<>(HttpStatusCode.valueOf(409));
        when(patientProxy.updatePatient(any(PatientBean.class), anyInt())).thenReturn(response);

        //WHEN we call the method
        String actualString = clientUIController.updatePatient(1, patient, result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void deletePatientTest(){
        //GIVEN we should get this String
        String expectedString = "patientListPage";

        //WHEN we call the method
        String actualString = clientUIController.deletePatient(1, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void showPatientNotePageTest(){
        //GIVEN we should get this string
        String expectedString = "patientNotePage";
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(new ArrayList<>());

        //WHEN we call the method
        String actualString = clientUIController.showPatientNotePage(1, new NoteBean(), model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void addNoteTest(){
        //GIVEN we should get this string
        String expectedString = "patientListPage";
        when(patientNoteProxy.addNote(any(NoteBean.class), anyInt())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(201)));

        //WHEN we call the method
        String actualString = clientUIController.addNote(1, new NoteBean(), result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void addNoteWhenErrorInTheFormTest(){
        //GIVEN we should get this string
        String expectedString = "patientNotePage";
        when(result.hasErrors()).thenReturn(true);

        //WHEN we call the method
        String actualString = clientUIController.addNote(1, new NoteBean(), result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void addNoteWhenNoteIsNotCreatedTest(){
        //GIVEN we should get this string
        String expectedString = "patientNotePage";
        when(patientNoteProxy.addNote(any(NoteBean.class), anyInt())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(409)));

        //WHEN we call the method
        String actualString = clientUIController.addNote(1, new NoteBean(), result, model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void showUpdateNotePageTest() {
        //GIVEN we should get this string
        String expectedString = "updateNotePage";
        when(patientNoteProxy.getNoteById(anyInt(), anyString())).thenReturn(new NoteBean());

        //WHEN we call the method
        String actualString = clientUIController.showUpdateNotePage(1, "1", model);

        //THEN we get the correct String
        assertEquals(expectedString, actualString);
    }

    @Test
    public void updateNoteTest() {
        //GIVEN we should get this string
        String expectedString = "patientListPage";
        when(patientNoteProxy.updateNote(anyInt(), anyString(), anyString())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        NoteBean noteBean = new NoteBean("1", 1, LocalDateTime.now(), "test");

        //WHEN we call the method
        String actualString = clientUIController.updateNote(1, "1", noteBean, result, model);

        //THEN we get the correct string
        assertEquals(expectedString, actualString);
    }

    @Test
    public void updateNoteWhenErrorInTheFormTest() {
        //GIVEN we should get this string
        String expectedString = "updateNotePage";
        when(result.hasErrors()).thenReturn(true);

        //WHEN we call the method
        String actualString = clientUIController.updateNote(1, "1", new NoteBean(), result, model);

        //THEN we get the correct string
        assertEquals(expectedString, actualString);
    }

    @Test
    public void updateNoteWhenNoteIsNotUpdatedTest() {
        //GIVEN we should get this string
        String expectedString = "updateNotePage";
        when(patientNoteProxy.updateNote(anyInt(), anyString(), anyString())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(404)));
        NoteBean noteBean = new NoteBean("1", 1, LocalDateTime.now(), "test");

        //WHEN we call the method
        String actualString = clientUIController.updateNote(1, "1", noteBean, result, model);

        //THEN we get the correct string
        assertEquals(expectedString, actualString);
    }
}
