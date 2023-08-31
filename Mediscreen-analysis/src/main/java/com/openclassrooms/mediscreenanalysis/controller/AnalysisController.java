package com.openclassrooms.mediscreenanalysis.controller;

import com.openclassrooms.mediscreenanalysis.model.Analysis;
import com.openclassrooms.mediscreenanalysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnalysisController {
    @Autowired
    private final AnalysisService analysisService;

    @PostMapping(value = "/patients/{id}/analysis")
    public String getAnalysisFromPatient(@PathVariable("id") Integer id, @RequestBody Analysis analysis) {
        return analysisService.getAnalysis(analysis);
    }
}
