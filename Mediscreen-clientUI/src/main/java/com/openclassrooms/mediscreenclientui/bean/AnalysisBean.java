package com.openclassrooms.mediscreenclientui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisBean {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String gender;
    private List<NoteBean> noteList;

    public AnalysisBean(PatientBean patientBean, List<NoteBean> noteBeanList) {
        this.firstName = patientBean.getFirstName();
        this.lastName = patientBean.getLastName();
        this.birthdate = patientBean.getBirthdate();
        this.gender = patientBean.getGender();
        this.noteList = noteBeanList;
    }
}
