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

    @RequestMapping("/patients/{id}")
    public String getPatient(@PathVariable int id, Model model){
        PatientBean patientBean = patientProxy.getPatient(id);
        model.addAttribute("patient", patientBean);
        return "patientPage";
    }

    @GetMapping("/addPatient")
    public String addPatientPage(PatientBean patient){
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
}