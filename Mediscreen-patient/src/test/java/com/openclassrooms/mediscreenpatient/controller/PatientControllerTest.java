package com.openclassrooms.mediscreenpatient.controller;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.model.Patient;
import com.openclassrooms.mediscreenpatient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientControllerTest {
    @MockBean
    private PatientController patientController;
    private PatientService patientService = mock(PatientService.class);

    @BeforeEach
    public void setupPerTest(){
        patientController = new PatientController(patientService);
    }

    @Test
    public void getAllPatientsTest(){
        //GIVEN this should call this method
        when(patientService.getAllPatient()).thenReturn(new ArrayList<>());

        //WHEN we call the method
        patientController.getAllPatients();

        //THEN patientService.getAllPatient is called
        verify(patientService, times(1)).getAllPatient();
    }

    @Test
    public void getPatientTest(){
        //GIVEN this should call this method
        Patient patient = new Patient();
        when(patientService.getPatientById(anyInt())).thenReturn(patient);

        //WHEN we call the method
        patientController.getPatient(1);

        //THEN patientService.getAllPatient is called
        verify(patientService, times(1)).getPatientById(anyInt());
    }

    @Test
    public void addPatientTest(){
        //GIVEN we should get this response
        ResponseEntity<PatientDto> expectedResponse = new ResponseEntity<>(HttpStatusCode.valueOf(201));
        Patient patient = new Patient();
        PatientDto patientDto = new PatientDto();
        when(patientService.addPatient(any(PatientDto.class))).thenReturn(patient);

        //WHEN we call the method
        ResponseEntity<PatientDto> actualResponse = patientController.addPatient(patientDto);

        //THEN we get the correct response
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        verify(patientService, times(1)).addPatient(any(PatientDto.class));
    }

    @Test
    public void updatePatientTest(){
        //GIVEN there is a patient to update
        ResponseEntity<PatientDto> expectedResponse = new ResponseEntity<>(HttpStatusCode.valueOf(200));
        PatientDto patientDto = new PatientDto();
        Patient patient = new Patient();
        when(patientService.updatePatient(anyInt(), any(PatientDto.class))).thenReturn(patient);

        //WHEN we call this method
        ResponseEntity<PatientDto> actualResponse = patientController.updatePatient(1, patientDto);

        //THEN patientService.updatePatient is called and we get the correct response
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        verify(patientService, times(1)).updatePatient(anyInt(), any(PatientDto.class));
    }

    @Test
    public void deletePatientTest(){
        //GIVEN there is a patient to delete
        ResponseEntity<Patient> expectedResponse = new ResponseEntity<>(HttpStatusCode.valueOf(200));
        doNothing().when(patientService).deletePatient(anyInt());

        //WHEN we call this method
        ResponseEntity<Patient> actualResponse = patientController.deletePatient(1);

        //THEN patientService.deletePatient is called and we get the correct response
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        verify(patientService, times(1)).deletePatient(anyInt());
    }
}
