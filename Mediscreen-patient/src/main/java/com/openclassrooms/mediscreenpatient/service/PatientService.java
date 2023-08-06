package com.openclassrooms.mediscreenpatient.service;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.model.Patient;

import java.util.List;

public interface PatientService {
    /**
     * get a list of all patients
     * @return return a list of Patient
     */
    List<Patient> getAllPatient();

    /**
     * get a patient from its id
     * @param id the id of the patient we would find
     * @return a Patient
     */
    Patient getPatientById(int id);

    /**
     * add a Patient in database
     * @param patientDto the informations of the patient we would add
     * @return the Patient added
     */
    Patient addPatient(PatientDto patientDto);

    /**
     * update the information of a patient
     * @param id the id of the patient we would update
     * @param patientDto the information of the patient to update
     * @return the patient updated
     */
    Patient updatePatient(Integer id, PatientDto patientDto);

    /**
     * delete a patient from the database
     * @param id the id of the patient we would delete
     */
    void deletePatient(Integer id);
}
