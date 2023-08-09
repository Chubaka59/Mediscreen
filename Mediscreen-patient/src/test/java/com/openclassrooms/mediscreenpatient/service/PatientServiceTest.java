package com.openclassrooms.mediscreenpatient.service;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.exception.PatientAlreadyExistException;
import com.openclassrooms.mediscreenpatient.exception.PatientNotFoundException;
import com.openclassrooms.mediscreenpatient.model.Patient;
import com.openclassrooms.mediscreenpatient.repository.PatientRepository;
import com.openclassrooms.mediscreenpatient.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PatientServiceTest {
    @MockBean
    private PatientService patientService;

    private final PatientRepository patientRepository = mock(PatientRepository.class);

    @BeforeEach
    public void setupPerTest() {
        patientService = new PatientServiceImpl(patientRepository);
    }

    @Test
    public void getAllPatientTest(){
        //GIVEN it should call this method
        List<Patient> expectedPatientList = new ArrayList<>();
        when(patientRepository.findAll()).thenReturn(expectedPatientList);

        //WHEN we call the method
        List<Patient> actualPatientList = patientService.getAllPatient();

        //THEN the correct method is called and we get the correct return
        assertEquals(expectedPatientList, actualPatientList);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void getPatientById(){
        //GIVEN this should return a patient
        Patient expectedPatient = new Patient();
        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(expectedPatient));

        //WHEN we try to get this patient
        Patient actualPatient = patientService.getPatientById(1);

        //THEN patientRepository.findById is called and we get the correct return
        assertEquals(expectedPatient, actualPatient);
        verify(patientRepository, times(1)).findById(anyInt());
    }

    @Test
    public void getPatientByIdWhenPatientIsNotFoundTest(){
        //GIVEN this should not find a patient
        when(patientRepository.findById(anyInt())).thenReturn(Optional.empty());

        //WHEN we try to get this patient THEN an exception is thrown
        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(1));
    }

    @Test
    public void addPatientTest(){
        //GIVEN the patient we would add doesn't exist
        when(patientRepository.findPatientByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.empty());
        PatientDto patientDto = new PatientDto("firstName", "lastName", LocalDate.now(), "M", "address", "phone");
        Patient patient = new Patient();
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        //WHEN we try to add this patient
        patientService.addPatient(patientDto);

        //THEN patientRepository.save is called and we get the correct return
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void addPatientWhenPatientAlreadyExistTest(){
        //GIVEN the patient we would add already exist
        Patient patient = new Patient(1, "firstName", "lastName", null, null, null, null);
        when(patientRepository.findPatientByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.of(patient));
        PatientDto patientDto = new PatientDto("firstName", "lastName", null, null, null, null);

        //WHEN we try to add the patient THEN an exception is thrown
        assertThrows(PatientAlreadyExistException.class, () -> patientService.addPatient(patientDto));
    }

    @Test
    public void updatePatientTest(){
        //GIVEN there is a patient to update
        Patient existingPatient = new Patient();
        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(existingPatient));
        PatientDto patientDto = new PatientDto("firstName", "lastName", LocalDate.now(), "M", "address", "phone");

        //WHEN we try to update the patient
        patientService.updatePatient(1, patientDto);

        //THEN patientRepository.save is called
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void deletePatientTest(){
        //GIVEN there is a patient to delete
        Patient existingPatient = new Patient();
        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(existingPatient));
        doNothing().when(patientRepository).delete(any(Patient.class));

        //WHEN we try to delete the patient
        patientService.deletePatient(1);

        //THEN patientRepository.delete is called
        verify(patientRepository, times(1)).delete(any(Patient.class));
    }
}
