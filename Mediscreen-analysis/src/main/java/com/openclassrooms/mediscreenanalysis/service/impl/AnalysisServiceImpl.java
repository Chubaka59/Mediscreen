package com.openclassrooms.mediscreenanalysis.service.impl;

import com.openclassrooms.mediscreenanalysis.model.Analysis;
import com.openclassrooms.mediscreenanalysis.model.Note;
import com.openclassrooms.mediscreenanalysis.service.AnalysisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private int countKeywords(List<Note> noteList) {
        int count = 0;
        List<String> keywordList = List.of("Hemoglobin A1C", "Microalbumin", "Height", "Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "AntiBodies");
        for (String keyword : keywordList) {
            for (Note note : noteList) {
                if (StringUtils.containsIgnoreCase(note.getNote(), keyword)) {
                    count += 1;
                    break;
                }
            }
        }
        return count;
    }

    public String getAnalysis(Analysis analysis) {
        int count = countKeywords(analysis.getNoteList());
        if (!analysis.isMoreThanThirty()) {
            if (analysis.isMale()) {
                if (0 <= count && count <= 2) {
                    return "None";
                } else if (3 <= count && count <= 4) {
                    return "In Danger";
                } else if (5 <= count) {
                    return "Early Onset";
                }
            } else {
                if (0 <= count && count <= 3) {
                    return "None";
                } else if (4 <= count && count <= 6) {
                    return "In Danger";
                } else if (7 <= count) {
                    return "Early Onset";
                }
            }
        } else {
            if (0 <= count && count <= 1) {
                return "None";
            } else if (2 <= count && count <= 5) {
                return "Borderline";
            } else if (6 <= count && count <= 7) {
                return "In Danger";
            } else if (8 <= count) {
                return "Early Onset";
            }
        }
        return "Unknown";
    }
}