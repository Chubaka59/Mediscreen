package com.openclassrooms.mediscreenpatient.service;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatient();
    Patient getPatientById(int id);
    Patient addPatient(PatientDto patientDto);
    Patient updatePatient(Integer id, PatientDto patientDto);
    void deletePatient(Integer id);
}
