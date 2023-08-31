package com.openclassrooms.mediscreenanalysis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Analysis {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String gender;
    private List<Note> noteList;

    public boolean isMoreThanThirty() {
        return Period.between(this.birthdate, LocalDate.now()).getYears() > 30;
    }

    public boolean isMale() {
        return this.gender.equals("M");
    }
}
