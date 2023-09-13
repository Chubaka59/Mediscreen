package com.openclassrooms.mediscreenclientui.proxy;

import com.openclassrooms.mediscreenclientui.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediscreen-patientNote")
public interface PatientNoteProxy {
    @GetMapping(value = "/patients/{id}/notes")
    List<NoteBean> getAllPatientNote(@PathVariable("id") int id);
    @PostMapping(value = "/patients/{id}/notes")
    ResponseEntity<String> addNote(@RequestBody NoteBean noteBean, @PathVariable Integer id);
    @GetMapping(value = "/patients/{patientId}/notes/{noteId}")
    NoteBean getNoteById(@PathVariable("patientId") Integer patientId, @PathVariable("noteId") String noteId);
    @PutMapping(value = "/patients/{patientId}/notes/{noteId}")
    ResponseEntity<String> updateNote(@PathVariable("patientId") Integer patientId, @PathVariable("noteId") String noteId, @RequestBody String note);
}
