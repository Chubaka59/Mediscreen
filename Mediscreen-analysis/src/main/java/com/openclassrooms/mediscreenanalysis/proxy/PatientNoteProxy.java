package com.openclassrooms.mediscreenanalysis.proxy;

import com.openclassrooms.mediscreenanalysis.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "mediscreen-patientNote")
public interface PatientNoteProxy {
    @GetMapping(value = "/patients/{id}/notes")
    List<NoteBean> getAllPatientNote(@PathVariable("id") int id);
}
