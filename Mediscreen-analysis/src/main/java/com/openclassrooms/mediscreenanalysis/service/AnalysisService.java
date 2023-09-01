package com.openclassrooms.mediscreenanalysis.service;

import com.openclassrooms.mediscreenanalysis.model.Analysis;

public interface AnalysisService {
    /**
     * proceed to the analysis of the notes of a patient
     * @param analysis the information to analyze
     * @return a String containing the result of the analysis
     */
    String getAnalysis(Analysis analysis);
}
