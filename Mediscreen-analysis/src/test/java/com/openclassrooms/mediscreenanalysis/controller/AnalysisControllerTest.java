package com.openclassrooms.mediscreenanalysis.controller;

import com.openclassrooms.mediscreenanalysis.service.AnalysisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

public class AnalysisControllerTest {
    @MockBean
    private AnalysisController analysisController;
    private final AnalysisService analysisService = mock(AnalysisService.class);

    @BeforeEach
    public void setupPerTest() {
        analysisController = new AnalysisController(analysisService);
    }

    @Test
    public void getAnalysisFromPatientTest() {
        //GIVEN we should call this method
        when(analysisService.getAnalysis(anyInt())).thenReturn("test");

        //WHEN we call this method
        analysisController.getAnalysisFromPatient(1);

        //THEN the correct method is called
        verify(analysisService, times(1)).getAnalysis(anyInt());
    }
}
