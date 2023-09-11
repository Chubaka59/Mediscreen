package com.openclassrooms.mediscreenanalysis.service.impl;

import com.openclassrooms.mediscreenanalysis.bean.NoteBean;
import com.openclassrooms.mediscreenanalysis.bean.PatientBean;
import com.openclassrooms.mediscreenanalysis.exception.PatientNotFoundException;
import com.openclassrooms.mediscreenanalysis.model.AnalysisResult;
import com.openclassrooms.mediscreenanalysis.proxy.PatientNoteProxy;
import com.openclassrooms.mediscreenanalysis.proxy.PatientProxy;
import com.openclassrooms.mediscreenanalysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private final PatientProxy patientProxy;
    @Autowired
    private final PatientNoteProxy patientNoteProxy;

    /**
     * check if the specified keywords appeared in the note list and increment the count.
     * @param noteList the note list to analyze
     * @return an int which is the count
     */
    private int countKeywords(List<String> noteList) {
        int count = 0;
        List<String> keywordList = List.of("Hemoglobin A1C", "Microalbumin", "Height", "Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "AntiBodies");
        for (String keyword : keywordList) {
            for (String note : noteList) {
                if (StringUtils.containsIgnoreCase(note, keyword)) {
                    count += 1;
                    break;
                }
            }
        }
        return count;
    }

    public String getAnalysis(Integer id) {
        PatientBean patientBean = patientProxy.getPatient(id);
        if (patientBean == null) {
            throw new PatientNotFoundException(id);
        }

        List<NoteBean> noteBeanList = patientNoteProxy.getAllPatientNote(id);

        //return none if list is empty, this avoids to enter the loop
        if (noteBeanList.isEmpty()) {
            return AnalysisResult.None.toString();
        }

        List<String> noteList = noteBeanList.stream().map(NoteBean::getNote).toList();

        int count = countKeywords(noteList);
        return getAnalysisResult(patientBean, count);
    }

    /**
     * return the result of the analysis following the below table
     *
     * if the patient is more than thirty years old
     * 0 <= count <= 1 result : None
     * 2 <= count <= 5 result : Borderline
     * 6 <= count <= 7 result : InDanger
     * 8 <= count      result : EarlyOnset
     *
     * if the patient is less than thirty and is a male
     * 0 <= count <= 2 result : None
     * 3 <= count <= 4 result : InDanger
     * 5 <= count      result : EarlyOnset
     *
     * if the patient is less than thirty and is a female
     * 0 <= count <= 3 result : None
     * 4 <= count <= 6 result : InDanger
     * 7 <= count      result : EarlyOnset
     *
     * @param patientBean the patient to analyze
     * @param count int that has been count from the patient's notes
     * @return the enum result of the analysis as a string
     */
    private static String getAnalysisResult(PatientBean patientBean, int count) {
        if (patientBean.isMoreThanThirty()) {
            if (0 <= count && count <= 1) {
                return AnalysisResult.None.toString();
            } else if (2 <= count && count <= 5) {
                return AnalysisResult.Borderline.toString();
            } else if (6 <= count && count <= 7) {
                return AnalysisResult.InDanger.toString();
            } else  {
                return AnalysisResult.EarlyOnset.toString();
            }
        }
        if (patientBean.isMale()) {
            if (0 <= count && count <= 2) {
                return AnalysisResult.None.toString();
            } else if (3 <= count && count <= 4) {
                return AnalysisResult.InDanger.toString();
            } else {
                return AnalysisResult.EarlyOnset.toString();
            }
        } else {
            if (0 <= count && count <= 3) {
                return AnalysisResult.None.toString();
            } else if (4 <= count && count <= 6) {
                return AnalysisResult.InDanger.toString();
            } else {
                return AnalysisResult.EarlyOnset.toString();
            }
        }
    }
}