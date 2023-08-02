package com.openclassrooms.mediscreenpatient.model;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate birthdate;
    @NotBlank
    private String gender;
    private String address;
    private String phone;

    public Patient(PatientDto patientDto){
        this.firstName = patientDto.getFirstName();
        this.lastName = patientDto.getLastName();
        this.birthdate = patientDto.getBirthdate();
        this.gender = patientDto.getGender();
        this.address = patientDto.getAddress();
        this.phone = patientDto.getPhone();
    }
}
