package com.openclassrooms.mediscreenpatient.service;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.exception.PatientAlreadyExistException;
import com.openclassrooms.mediscreenpatient.exception.PatientNotFoundException;
import com.openclassrooms.mediscreenpatient.model.Patient;
import com.openclassrooms.mediscreenpatient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }

    public Patient getPatient(int id){
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
}
