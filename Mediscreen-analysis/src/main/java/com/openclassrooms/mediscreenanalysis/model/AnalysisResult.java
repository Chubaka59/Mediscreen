package com.openclassrooms.mediscreenanalysis.model;

public enum AnalysisResult {
    None ("None"),
    Borderline ("Borderline"),
    InDanger ("In Danger"),
    EarlyOnset ("Early Onset");

    private final String name;
    AnalysisResult(String result) {
        name = result;
    }

    public String toString() {
        return this.name;
    }
}
