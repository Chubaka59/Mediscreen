package com.openclassrooms.mediscreenpatientnote.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "patientNote")
@Data
public class PatientNote {
    @Id
    private String id;
    @Indexed(unique = true)
    private int patientId;
    private List<Note> noteList;
}
