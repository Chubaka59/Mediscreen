package com.openclassrooms.mediscreenpatient.service.impl;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.exception.PatientAlreadyExistException;
import com.openclassrooms.mediscreenpatient.exception.PatientNotFoundException;
import com.openclassrooms.mediscreenpatient.model.Patient;
import com.openclassrooms.mediscreenpatient.repository.PatientRepository;
import com.openclassrooms.mediscreenpatient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    public Patient addPatient(PatientDto patientDto){
        Optional<Patient> existingPatient = patientRepository.findPatientByFirstNameAndLastName(patientDto.getFirstName(), patientDto.getLastName());
        if(existingPatient.isPresent()){
            throw new PatientAlreadyExistException(patientDto.getFirstName(), patientDto.getLastName());
        }
        Patient newPatient = new Patient(patientDto);
        return patientRepository.save(newPatient);
    }

    public Patient updatePatient(Integer id, PatientDto patientDto) {
        Patient updatedPatient = getPatientById(id).update(patientDto);
        return patientRepository.save(updatedPatient);
    }

    public void deletePatient(Integer id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }
}
