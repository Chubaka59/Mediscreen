package com.openclassrooms.mediscreenpatient.controller;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.model.Patient;
import com.openclassrooms.mediscreenpatient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {
    @Autowired
    private final PatientService patientService;

    @GetMapping(value = "/patients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatient();
    }

    @GetMapping(value = "/patients/{id}")
    public Patient getPatient(@PathVariable int id){
        return patientService.getPatientById(id);
    }

    @PostMapping(value = "/patients")
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patient){
        patientService.addPatient(patient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/patients/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("id") Integer id, @RequestBody PatientDto patientDto){
        patientService.updatePatient(id, patientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/patients/{id}/delete")
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") Integer id){
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
