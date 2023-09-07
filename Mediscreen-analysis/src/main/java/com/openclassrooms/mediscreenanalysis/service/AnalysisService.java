package com.openclassrooms.mediscreenanalysis.service;

public interface AnalysisService {
    /**
     * Analyze patient's notes to return the result a of the analysis
     * @param id the id of the patient to analyze
     * @return a String which is the result
     */
    String getAnalysis(Integer id);
}
