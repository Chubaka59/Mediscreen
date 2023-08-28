package com.openclassrooms.mediscreenpatientnote.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mediscreenpatientnote.model.Note;
import com.openclassrooms.mediscreenpatientnote.repository.PatientNoteRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatientNoteIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PatientNoteRepository patientNoteRepository;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getNoteListByPatientIdTest() throws Exception {
        //GIVEN notes are already existing in db
        Note existingNote1 = new Note("1", 1, LocalDateTime.now(), "testNote1");
        patientNoteRepository.save(existingNote1);
        Note existingNote2 = new Note("2", 1, LocalDateTime.now(), "testNote2");
        patientNoteRepository.save(existingNote2);

        //WHEN we try to get them
        MvcResult mvcResult = mockMvc.perform(get("/patients/1/notes"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        //THEN we get the notes as return
        String result = mvcResult.getResponse().getContentAsString();
        assertTrue(result.contains(existingNote1.getNote()) && result.contains(existingNote2.getNote()));

        //remove test entries
        patientNoteRepository.deleteAll();
    }

    @Test
    public void addNoteTest() throws Exception {
        int initialCount = patientNoteRepository.findAll().size();
        Note noteToAdd = new Note(null, null, null, "testNoteToAdd");

        mockMvc.perform(post("/patients/2/notes")
                        .content(objectToJson(noteToAdd))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

        System.out.println(patientNoteRepository.findAll());
        assertEquals(initialCount + 1, patientNoteRepository.findAll().size());

        //remove test entries
        patientNoteRepository.deleteAll();
    }

    @SneakyThrows
    private String objectToJson(Object o){
        return  mapper.writeValueAsString(o);
    }
}