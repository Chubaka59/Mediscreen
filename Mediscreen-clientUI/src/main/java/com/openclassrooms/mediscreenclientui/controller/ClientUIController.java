package com.openclassrooms.mediscreenclientui.controller;

import com.openclassrooms.mediscreenclientui.bean.PatientBean;
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

@Controller
public class ClientUIController {
    @Autowired
    private PatientProxy patientProxy;

    @RequestMapping("/patients")
    public String getAllPatients(Model model){
        model.addAttribute("patients", patientProxy.getallPatients());
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
            model.addAttribute("patients", patientProxy.getallPatients());
            return "patientListPage";
        }
        return "addPatientPage";
    }

    @GetMapping("/patients/{id}")
    public String showUpdatePage(@PathVariable("id") Integer id, Model model){
        model.addAttribute("patient", patientProxy.getPatient(id));
        return "updatePatientPage";
    }

    @PostMapping("/patients/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patient, BindingResult result, Model model){
        if (result.hasErrors()){
            return "updatePatientPage";
        }
        ResponseEntity<PatientBean> response = patientProxy.updatePatient(patient, id);
        if (response.getStatusCode() == HttpStatus.OK){
            model.addAttribute("patients", patientProxy.getallPatients());
            return "patientListPage";
        }
        return "updatePatientPage";
    }

    @GetMapping("patients/{id}/delete")
    public String deletePatient(@PathVariable("id") Integer id, Model model){
        patientProxy.deletePatient(id);
        model.addAttribute("patients", patientProxy.getallPatients());
        return "patientListPage";
    }
}