package com.openclassrooms.mediscreenpatientnote.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "patientNote")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    private String id;
    private Integer patientId;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private String note;

    public Note update(String note) {
        this.note = note;
        return this;
    }
}
