package com.openclassrooms.mediscreenanalysis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private String noteId;
    private int patientId;
    private LocalDateTime date;
    private String note;
}
