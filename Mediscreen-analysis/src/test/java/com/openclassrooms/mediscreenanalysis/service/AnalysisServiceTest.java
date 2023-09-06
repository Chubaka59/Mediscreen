package com.openclassrooms.mediscreenanalysis.service;

import com.openclassrooms.mediscreenanalysis.service.impl.AnalysisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalysisServiceTest {
    @MockBean
    private AnalysisService analysisService;

    @BeforeEach
    public void setupPerTest() {
        analysisService = new AnalysisServiceImpl();
    }

    Note note1 = new Note(null, 1, null, "Microalbumin");
    Note note2 = new Note(null, 1, null, "Hemoglobin A1C");
    Note note3 = new Note(null, 1, null, "Height");
    Note note4 = new Note(null, 1, null, "Weight");
    Note note5 = new Note(null, 1, null, "Smoker");
    Note note6 = new Note(null, 1, null, "Abnormal");
    Note note7 = new Note(null, 1, null, "Cholesterol");
    Note note8 = new Note(null, 1, null, "Dizziness");

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetNoneTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"M", noteList);
        String expectedString = "None";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetInDangerTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"M", noteList);
        String expectedString = "In Danger";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMaleLessThanThirtyShouldGetEarlyOnsetTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"M", noteList);
        String expectedString = "Early Onset";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetNoneTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"F", noteList);
        String expectedString = "None";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetInDangerTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3, note4, note5);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"F", noteList);
        String expectedString = "In Danger";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenFemaleLessThanThirtyShouldGetEarlyOnsetTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6, note7);
        Analysis analysis = new Analysis("test", "test", LocalDate.now(),"F", noteList);
        String expectedString = "Early Onset";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetNoneTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"F", noteList);
        String expectedString = "None";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetBorderlineTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3, note4, note5);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"M", noteList);
        String expectedString = "Borderline";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetInDangerTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"M", noteList);
        String expectedString = "In Danger";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getAnalysisWhenMoreThanThirtyShouldGetEarlyOnsetTest() {
        //GIVEN the analysis should return None
        List<Note> noteList = List.of(note1, note2, note3, note4, note5, note6, note7, note8);
        Analysis analysis = new Analysis("test", "test", LocalDate.now().minusYears(31),"F", noteList);
        String expectedString = "Early Onset";

        //WHEN we analyze
        String actualString = analysisService.getAnalysis(analysis);

        //THEN we get the correct return
        assertEquals(expectedString, actualString);
    }
}
