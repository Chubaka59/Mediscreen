package com.openclassrooms.mediscreenpatient.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import com.openclassrooms.mediscreenpatient.model.Patient;
import com.openclassrooms.mediscreenpatient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PatientIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getAllPatientsTest() throws Exception {
        mockMvc.perform(get("/patients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientTest() throws Exception {
        mockMvc.perform(get("/patients/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientNotFoundTest() throws Exception {
        mockMvc.perform(get("/patients/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPatientTest() throws Exception {
        int initialCount = patientRepository.findAll().size();

        PatientDto patient = new PatientDto("firstName", "lastName", LocalDate.now(), "M", "address", "phone");

        mockMvc.perform(post("/patients")
                                .content(objectToJson(patient))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
        assertEquals(initialCount + 1, patientRepository.findAll().size());
    }

    @Test
    public void addPatientThatAlreadyExistTest() throws Exception {
        int initialCount = patientRepository.findAll().size();

        //Trying to add a patient that already exist in database
        PatientDto patient = new PatientDto("existingFirstName", "existingLastName", LocalDate.now(), "M", "address", "phone");

        mockMvc.perform(post("/patients")
                        .content(objectToJson(patient))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict());

        assertEquals(initialCount, patientRepository.findAll().size());
    }

    @Test
    public void updatePatientTest() throws Exception {
        PatientDto patient = new PatientDto("firstNameUpdated", "lastNameUpdated", LocalDate.now(), "F", "addressUpdated", "phoneUpdated");

        mockMvc.perform(put("/patients/1")
                        .content(objectToJson(patient))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Patient actualPatient = patientRepository.findById(1).get();

        assertEquals(patient.getFirstName(), actualPatient.getFirstName());
        assertEquals(patient.getLastName(), actualPatient.getLastName());
        assertEquals(patient.getBirthdate(), actualPatient.getBirthdate());
        assertEquals(patient.getGender(), actualPatient.getGender().toString());
        assertEquals(patient.getAddress(), actualPatient.getAddress());
        assertEquals(patient.getPhone(), actualPatient.getPhone());
    }

    @Test
    public void updatePatientThatDoesNotExistTest() throws Exception {
        PatientDto patient = new PatientDto("firstNameUpdated", "lastNameUpdated", LocalDate.now(), "F", "addressUpdated", "phoneUpdated");

        mockMvc.perform(put("/patients/2")
                        .content(objectToJson(patient))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

        Patient actualPatient = patientRepository.findById(1).get();

        assertNotEquals(patient.getFirstName(), actualPatient.getFirstName());
        assertNotEquals(patient.getLastName(), actualPatient.getLastName());
        assertNotEquals(patient.getBirthdate(), actualPatient.getBirthdate());
        assertNotEquals(patient.getGender(), actualPatient.getGender().toString());
        assertNotEquals(patient.getAddress(), actualPatient.getAddress());
        assertNotEquals(patient.getPhone(), actualPatient.getPhone());
    }

    @Test
    public void deletePatientTest() throws Exception {
        int initialCount = patientRepository.findAll().size();

        mockMvc.perform(get("/patients/1/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        assertEquals(initialCount - 1, patientRepository.findAll().size());
    }

    @Test
    public void deletePatientThatDoesNotExistTest() throws Exception {
        int initialCount = patientRepository.findAll().size();

        mockMvc.perform(get("/patients/2/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

        assertEquals(initialCount, patientRepository.findAll().size());
    }


    @SneakyThrows
    private String objectToJson(Object o){
        return  mapper.writeValueAsString(o);

    }

}
