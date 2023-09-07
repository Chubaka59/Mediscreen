package com.openclassrooms.mediscreenanalysis.service;

import com.openclassrooms.mediscreenanalysis.bean.NoteBean;
import com.openclassrooms.mediscreenanalysis.bean.PatientBean;
import com.openclassrooms.mediscreenanalysis.exception.PatientNotFoundException;
import com.openclassrooms.mediscreenanalysis.model.AnalysisResult;
import com.openclassrooms.mediscreenanalysis.proxy.PatientNoteProxy;
import com.openclassrooms.mediscreenanalysis.proxy.PatientProxy;
import com.openclassrooms.mediscreenanalysis.service.impl.AnalysisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AnalysisServiceTest {
    @MockBean
    private AnalysisService analysisService;
    @Mock
    private PatientProxy patientProxy;
    @Mock
    private PatientNoteProxy patientNoteProxy;

    @BeforeEach
    public void setupPerTest() {
        analysisService = new AnalysisServiceImpl(patientProxy, patientNoteProxy);
    }

    NoteBean note1 = new NoteBean(null, 1, null, "Microalbumin");
    NoteBean note2 = new NoteBean(null, 1, null, "Hemoglobin A1C");
    NoteBean note3 = new NoteBean(null, 1, null, "Height");
    NoteBean note4 = new NoteBean(null, 1, null, "Weight");
    NoteBean note5 = new NoteBean(null, 1, null, "Smoker");
    NoteBean note6 = new NoteBean(null, 1, null, "Abnormal");
    NoteBean note7 = new NoteBean(null, 1, null, "Cholesterol");
    NoteBean note8 = new NoteBean(null, 1, null, "Dizziness");

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetNoneTest() {
        //GIVEN the analysis should return None
        List<NoteBean> noteList = List.of(note1);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.None.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetInDangerTest() {
        //GIVEN the analysis should return InDanger
        List<NoteBean> noteList = List.of(note1, note2, note3);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.InDanger.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetEarlyOnsetTest() {
        //GIVEN the analysis should return EarlyOnset
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.EarlyOnset.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetNoneTest() {
        //GIVEN the analysis should return None
        List<NoteBean> noteList = List.of(note1, note2, note3);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.None.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetInDangerTest() {
        //GIVEN the analysis should return InDanger
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.InDanger.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetEarlyOnsetTest() {
        //GIVEN the analysis should return EarlyOnset
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6, note7);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now(),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.EarlyOnset.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetNoneTest() {
        //GIVEN the analysis should return None
        List<NoteBean> noteList = List.of(note1);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.None.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetBorderlineTest() {
        //GIVEN the analysis should return Borderline
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.Borderline.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetInDangerTest() {
        //GIVEN the analysis should return InDanger
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"M");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.InDanger.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetEarlyOnsetTest() {
        //GIVEN the analysis should return EarlyOnset
        List<NoteBean> noteList = List.of(note1, note2, note3, note4, note5, note6, note7, note8);
        when(patientNoteProxy.getAllPatientNote(anyInt())).thenReturn(noteList);
        PatientBean patientBean = new PatientBean(1, "test", "test", LocalDate.now().minusYears(31),"F");
        when(patientProxy.getPatient(anyInt())).thenReturn(patientBean);
        String expectedString = AnalysisResult.EarlyOnset.toString();

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(1);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenPatientIsNotFoundTest() {
        //GIVEN the patient won't be found
        when(patientProxy.getPatient(anyInt())).thenReturn(null);

        //WHEN we analyze THEN an exception is thrown
        assertThrows(PatientNotFoundException.class, () -> analysisService.getAnalysis(1));
    }
}
