package com.openclassrooms.mediscreenclientui.integration;

import com.openclassrooms.mediscreenclientui.bean.NoteBean;
import com.openclassrooms.mediscreenclientui.bean.PatientBean;
import com.openclassrooms.mediscreenclientui.proxy.PatientNoteProxy;
import com.openclassrooms.mediscreenclientui.proxy.PatientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientUIIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientProxy patientProxy;
    @MockBean
    private PatientNoteProxy patientNoteProxy;

    @Test
    public void getAllPatientsTest() throws Exception {
        mockMvc.perform(get("/patients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attributeExists("patients"))
                .andExpect(status().isOk())
                .andExpect(view().name("patientListPage"));
    }

    @Test
    public void showAddPageTest() throws Exception {
        mockMvc.perform(get("/addPatient"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("addPatientPage"));
    }

    @Test
    public void addPatientTest() throws Exception {
        when(patientProxy.addPatient(any(PatientBean.class))).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(201)));

        mockMvc.perform(post("/patients")
                        .param("firstName", "firstNameToAdd")
                        .param("lastName", "lastNameToAdd")
                        .param("birthdate", "2000-01-01")
                        .param("gender", "M")
                        .param("address", "addressToAdd")
                        .param("phone", "phoneToAdd")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patients"))
                .andExpect(view().name("patientListPage"));
    }

    @Test
    public void addPatientWhenFormHasErrorTest() throws Exception {
        mockMvc.perform(post("/patients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("addPatientPage"));
    }

    @Test
    public void addPatientWhenPatientAlreadyExistTest() throws Exception {
        when(patientProxy.addPatient(any(PatientBean.class))).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(409)));

        mockMvc.perform(post("/patients")
                        .param("firstName", "firstNameToAdd")
                        .param("lastName", "lastNameToAdd")
                        .param("birthdate", "2000-01-01")
                        .param("gender", "M")
                        .param("address", "addressToAdd")
                        .param("phone", "phoneToAdd")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("addPatientPage"));
    }

    @Test
    public void showUpdatePageTest() throws Exception {
        PatientBean patientBean = new PatientBean();
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        mockMvc.perform(get("/patients/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attributeExists("patientBean"))
                .andExpect(view().name("updatePatientPage"));
    }

    @Test
    public void updatePatientTest() throws Exception {
        when(patientProxy.updatePatient(any(PatientBean.class), anyInt())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));

        mockMvc.perform(post("/patients/1")
                        .param("firstName", "firstNameToUpdate")
                        .param("lastName", "lastNameToUpdate")
                        .param("birthdate", "2000-01-01")
                        .param("gender", "M")
                        .param("address", "addressToUpdate")
                        .param("phone", "phoneToUpdate")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patients"))
                .andExpect(view().name("patientListPage"));
    }

    @Test
    public void updatePatientWhenErrorInTheFormTest() throws Exception {
        mockMvc.perform(post("/patients/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("updatePatientPage"));
    }

    @Test
    public void updatePatientWhenPatientDoesNotExistTest() throws Exception {
        when(patientProxy.updatePatient(any(PatientBean.class), anyInt())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(404)));

        mockMvc.perform(post("/patients/1")
                        .param("firstName", "firstNameToUpdate")
                        .param("lastName", "lastNameToUpdate")
                        .param("birthdate", "2000-01-01")
                        .param("gender", "M")
                        .param("address", "addressToUpdate")
                        .param("phone", "phoneToUpdate")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("updatePatientPage"));
    }

    @Test
    public void deletePatientTest() throws Exception {
        when(patientProxy.deletePatient(anyInt())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));

        mockMvc.perform(get("/patients/1/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attributeExists("patients"))
                .andExpect(view().name("patientListPage"));
    }

    @Test
    public void showPatientNotePageTest() throws Exception {
        when(patientProxy.getPatient(anyInt())).thenReturn(new PatientBean());
        LocalDateTime date = LocalDateTime.now();
        NoteBean existingNoteBean = new NoteBean(1, date, "\r\n");
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(List.of(existingNoteBean));
        NoteBean expectedNoteBean = new NoteBean(1, date, "<br />");

        mockMvc.perform(get("/patients/1/notes"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attributeExists("patientBean"))
                .andExpect(model().attributeExists("notes"))
                .andExpect(model().attribute("notes", List.of(expectedNoteBean)))
                .andExpect(view().name("patientNotePage"))
                .andExpect(status().isOk());
    }

    @Test
    public void addNoteTest() throws Exception {
        when(patientNoteProxy.addNote(any(NoteBean.class), anyInt())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(201)));

        mockMvc.perform(post("/patients/1/notes")
                        .param("note", "note"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attributeExists("patients"))
                .andExpect(view().name("patientListPage"))
                .andExpect(status().isOk());
    }

    @Test
    public void addNoteWhenErrorInTheFormTest() throws Exception {
        when(patientProxy.getPatient(anyInt())).thenReturn(new PatientBean());

        mockMvc.perform(post("/patients/1/notes"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("patientNotePage"))
                .andExpect(status().isOk());
    }

    @Test
    public void addNoteWhenNoteIsNotCreatedTest() throws Exception {
        when(patientNoteProxy.addNote(any(NoteBean.class), anyInt())).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(409)));
        when(patientProxy.getPatient(anyInt())).thenReturn(new PatientBean());

        mockMvc.perform(post("/patients/1/notes")
                .param("note", "note"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("patientNotePage"))
                .andExpect(status().isOk());
    }
}
