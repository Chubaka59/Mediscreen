package com.openclassrooms.mediscreenpatient.model;

import com.openclassrooms.mediscreenpatient.dto.PatientDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @NotNull
    @Column(columnDefinition = "ENUM('MALE', 'FEMALE')")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    private String phone;

    public Patient(PatientDto patientDto){
        this.firstName = patientDto.getFirstName();
        this.lastName = patientDto.getLastName();
        this.birthdate = patientDto.getBirthdate();
        this.gender = Gender.valueOf(patientDto.getGender());
        this.address = patientDto.getAddress();
        this.phone = patientDto.getPhone();
    }

    public Patient update(PatientDto patientDto){
        this.firstName = patientDto.getFirstName();
        this.lastName = patientDto.getLastName();
        this.birthdate = patientDto.getBirthdate();
        this.gender = Gender.valueOf(patientDto.getGender());
        this.address = patientDto.getAddress();
        this.phone = patientDto.getPhone();
        return this;
    }
}
