package com.openclassrooms.mediscreenanalysis.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mediscreenanalysis.bean.NoteBean;
import com.openclassrooms.mediscreenanalysis.bean.PatientBean;
import com.openclassrooms.mediscreenanalysis.model.AnalysisResult;
import com.openclassrooms.mediscreenanalysis.proxy.PatientNoteProxy;
import com.openclassrooms.mediscreenanalysis.proxy.PatientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnalysisIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientProxy patientProxy;
    @MockBean
    private PatientNoteProxy patientNoteProxy;
    @Autowired
    private ObjectMapper mapper;

    NoteBean note1 = new NoteBean(null, 1, null, "Microalbumin");
    NoteBean note2 = new NoteBean(null, 1, null, "Hemoglobin A1C");
    NoteBean note3 = new NoteBean(null, 1, null, "Height");
    NoteBean note4 = new NoteBean(null, 1, null, "Weight");
    NoteBean note5 = new NoteBean(null, 1, null, "Smoker");
    NoteBean note6 = new NoteBean(null, 1, null, "Abnormal");
    NoteBean note7 = new NoteBean(null, 1, null, "Cholesterol");
    NoteBean note8 = new NoteBean(null, 1, null, "Dizziness");

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetNoneTest() throws Exception {
        List<NoteBean> noteList = List.of(note1);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.None.toString()));
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetInDangerTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.InDanger.toString()));
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetEarlyOnsetTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.EarlyOnset.toString()));
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetNoneTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.None.toString()));
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetInDangerTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.InDanger.toString()));
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetEarlyOnsetTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6, note7);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.EarlyOnset.toString()));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetNoneTest() throws Exception {
        List<NoteBean> noteList = List.of(note1);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.None.toString()));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetBorderlineTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.Borderline.toString()));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetInDangerTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.InDanger.toString()));
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetEarlyOnsetTest() throws Exception {
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6, note7, note8);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.EarlyOnset.toString()));
    }

    @Test
    public void getAnalysisWhenPatientIsNotFoundTest() throws Exception {
        when(patientProxy.getPatient(anyInt())).thenReturn(null);

        mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAnalysisWhenNoteListIsEmptyTest() throws Exception {
        when(patientProxy.getPatient(anyInt())).thenReturn(new PatientBean());
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(new ArrayList<>());

        MvcResult result = mockMvc.perform(get("/patients/1/analysis"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(AnalysisResult.None.toString()));
    }
}
