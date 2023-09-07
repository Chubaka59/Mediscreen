package com.openclassrooms.mediscreenanalysis.proxy;

import com.openclassrooms.mediscreenanalysis.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Feign config when running locally
@FeignClient(name = "mediscreen-patientNote", url = "localhost:9002")
//Feign config when running with docker
//@FeignClient(name = "mediscreen-patientNote", url = "mediscreen-patientnote:9002")
public interface PatientNoteProxy {
    @GetMapping(value = "/patients/{id}/notes")
    List<NoteBean> getAllPatientNote(@PathVariable("id") int id);
}
