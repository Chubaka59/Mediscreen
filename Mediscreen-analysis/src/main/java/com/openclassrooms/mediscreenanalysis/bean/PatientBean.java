package com.openclassrooms.mediscreenanalysis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientBean {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String gender;

    public boolean isMoreThanThirty() {
        return Period.between(this.birthdate, LocalDate.now()).getYears() > 30;
    }

    public boolean isMale() {
        return this.gender.equals("M");
    }
}
