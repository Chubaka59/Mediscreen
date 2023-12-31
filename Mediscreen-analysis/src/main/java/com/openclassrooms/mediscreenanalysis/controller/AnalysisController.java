package com.openclassrooms.mediscreenanalysis.controller;

import com.openclassrooms.mediscreenanalysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnalysisController {
    @Autowired
    private final AnalysisService analysisService;

    @GetMapping(value = "/patients/{id}/analysis")
    public String getAnalysisFromPatient(@PathVariable("id") Integer id) {
        return analysisService.getAnalysis(id);
    }
}
