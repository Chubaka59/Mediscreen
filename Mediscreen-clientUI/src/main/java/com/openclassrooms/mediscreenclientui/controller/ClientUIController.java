package com.openclassrooms.mediscreenclientui.controller;

import com.openclassrooms.mediscreenclientui.bean.NoteBean;
import com.openclassrooms.mediscreenclientui.bean.PatientBean;
import com.openclassrooms.mediscreenclientui.proxy.PatientNoteProxy;
import com.openclassrooms.mediscreenclientui.proxy.PatientProxy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientUIController {
    @Autowired
    private PatientProxy patientProxy;
    @Autowired
    private PatientNoteProxy patientNoteProxy;

    @RequestMapping("/patients")
    public String getAllPatients(Model model){
        model.addAttribute("patients", patientProxy.getAllPatients());
        return "patientListPage";
    }

    @GetMapping("/addPatient")
    public String showAddPage(PatientBean patient){
        return "addPatientPage";
    }

    @PostMapping("/patients")
    public String addPatient(@Valid PatientBean patient, BindingResult result, Model model){
        if (result.hasErrors()){
            return "addPatientPage";
        }
        ResponseEntity<PatientBean> response = patientProxy.addPatient(patient);
        if(response.getStatusCode() == HttpStatus.CREATED){
            model.addAttribute("patients", patientProxy.getAllPatients());
            return "patientListPage";
        }
        return "addPatientPage";
    }

    @GetMapping("/patients/{id}")
    public String showUpdatePage(@PathVariable("id") Integer id, Model model){
        model.addAttribute("patientBean", patientProxy.getPatient(id));
        return "updatePatientPage";
    }

    @PostMapping("/patients/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patientBean,
                                BindingResult result, Model model){
        if (result.hasErrors()){
            return "updatePatientPage";
        }
        ResponseEntity<PatientBean> response = patientProxy.updatePatient(patientBean, id);
        if (response.getStatusCode() == HttpStatus.OK){
            model.addAttribute("patients", patientProxy.getAllPatients());
            return "patientListPage";
        }
        return "updatePatientPage";
    }

    @GetMapping("patients/{id}/delete")
    public String deletePatient(@PathVariable("id") Integer id, Model model){
        patientProxy.deletePatient(id);
        model.addAttribute("patients", patientProxy.getAllPatients());
        return "patientListPage";
    }

    @GetMapping("/patients/{id}/notes")
    public String showPatientNotePage(@PathVariable("id") Integer id, NoteBean note, Model model){
        PatientBean patientBean = patientProxy.getPatient(id);
        List<NoteBean> noteBeanList = patientNoteProxy.getAllPatientNote(id);
        noteBeanList.forEach(s -> s.setNote(s.getNote().replaceAll("\r\n", "<br />")));
        model.addAttribute("patientBean", patientBean);
        model.addAttribute("notes", noteBeanList);
        return "patientNotePage";
    }

    @PostMapping("/patients/{id}/notes")
    public String addNote(@PathVariable("id") Integer id, @Valid NoteBean note, BindingResult result, Model model){
        if (result.hasErrors()){
            return showPatientNotePage(id, note, model);
        }
        ResponseEntity<String> response = patientNoteProxy.addNote(note, id);
        if(response.getStatusCode() == HttpStatus.CREATED){
            model.addAttribute("patients", patientProxy.getAllPatients());
            return "patientListPage";
        }
        return showPatientNotePage(id, note, model);
    }

    @GetMapping("/patients/{patientId}/notes/{noteId}")
    public String showUpdateNotePage(@PathVariable("patientId") Integer patientId, @PathVariable("noteId") String noteId, Model model) {
        model.addAttribute("noteBean", patientNoteProxy.getNoteById(patientId, noteId));
        return "updateNotePage";
    }

    @PostMapping("/patients/{patientId}/notes/{noteId}")
    public String updateNote(@PathVariable("patientId") Integer patientId, @PathVariable("noteId") String noteId, @Valid NoteBean noteBean,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            return "updateNotePage";
        }
        ResponseEntity<String> response = patientNoteProxy.updateNote(patientId, noteId, noteBean.getNote());
        if (response.getStatusCode() == HttpStatus.OK){
            model.addAttribute("patients", patientProxy.getAllPatients());
            return "patientListPage";
        }
        return "patientNotePage";
    }
}