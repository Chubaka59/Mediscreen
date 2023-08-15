package com.openclassrooms.mediscreenpatientnote.repository;

import com.openclassrooms.mediscreenpatientnote.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientNoteRepository extends MongoRepository<PatientNote, String> {
    Optional<PatientNote> findByPatientId(Integer id);
}
