package com.openclassrooms.mediscreenanalysis.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnalysisIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    Note note1 = new Note(null, 1, null, "Microalbumin");
    Note note2 = new Note(null, 1, null, "Hemoglobin A1C");
    Note note3 = new Note(null, 1, null, "Height");
    Note note4 = new Note(null, 1, null, "Weight");
    Note note5 = new Note(null, 1, null, "Smoker");
    Note note6 = new Note(null, 1, null, "Abnormal");
    Note note7 = new Note(null, 1, null, "Cholesterol");
    Note note8 = new Note(null, 1, null, "Dizziness");

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetNoneTest() throws Exception {
        List<Note> noteList = List.of(note1);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"M", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("None"));
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetInDangerTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"M", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("In Danger"));
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetEarlyOnsetTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"M", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Early Onset"));
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetNoneTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"F", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("None"));
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetInDangerTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3, note4, note5);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"F", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("In Danger"));
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetEarlyOnsetTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6, note7);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"F", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Early Onset"));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetNoneTest() throws Exception {
        List<Note> noteList = List.of(note1);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"F", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("None"));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetBorderlineTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3, note4, note5);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"M", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Borderline"));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetInDangerTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"M", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("In Danger"));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetEarlyOnsetTest() throws Exception {
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6, note7, note8);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"F", noteList);

        MvcResult result = mockMvc.perform(post("/patients/1/analysis")
                        .content(objectToJson(analysis))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Early Onset"));
    }

    @SneakyThrows
    private String objectToJson(Object o){
        return  mapper.writeValueAsString(o);
    }
}
