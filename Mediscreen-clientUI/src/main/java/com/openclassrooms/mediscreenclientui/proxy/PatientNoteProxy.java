package com.openclassrooms.mediscreenclientui.proxy;

import com.openclassrooms.mediscreenclientui.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "mediscreen-patientNote", url = "localhost:9002")
public interface PatientNoteProxy {
    @GetMapping(value = "/patients/{id}/notes")
    List<NoteBean> getAllPatientNote(@PathVariable("id") int id);
    @PostMapping(value = "/patients/{id}/notes")
    ResponseEntity<String> addNote(@RequestBody NoteBean noteBean, @PathVariable Integer id);
}
