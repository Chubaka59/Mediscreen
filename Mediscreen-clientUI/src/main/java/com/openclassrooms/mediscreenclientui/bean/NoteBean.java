package com.openclassrooms.mediscreenclientui.bean;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String note;
}
