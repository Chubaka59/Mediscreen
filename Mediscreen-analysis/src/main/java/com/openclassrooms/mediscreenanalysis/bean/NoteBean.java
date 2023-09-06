package com.openclassrooms.mediscreenanalysis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteBean {
    private String noteId;
    private int patientId;
    private LocalDateTime date;
    private String note;
}
