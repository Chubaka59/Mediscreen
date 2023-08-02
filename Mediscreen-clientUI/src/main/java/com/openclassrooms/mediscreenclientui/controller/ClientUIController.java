package com.openclassrooms.mediscreenclientui.controller;

import com.openclassrooms.mediscreenclientui.bean.PatientBean;
import com.openclassrooms.mediscreenclientui.proxy.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientUIController {
    @Autowired
    private PatientProxy patientProxy;

    @RequestMapping("/patients")
    public String getAllPatients(Model model){
        List<PatientBean> patientBeanList = patientProxy.getallPatients();
        model.addAttribute("patients", patientBeanList);
        return "patientListPage";
    }

    @RequestMapping("/patients/{id}")
    public String getPatient(@PathVariable int id, Model model){
        PatientBean patientBean = patientProxy.getPatient(id);
        model.addAttribute("patient", patientBean);
        return "patientPage";
    }

    @PostMapping("/patients")
    public String addPatient(@RequestBody PatientBean patient){
        ResponseEntity<PatientBean> response = patientProxy.addPatient(patient);
        if(response.getStatusCode() == HttpStatus.CREATED){
            return "patientListPage";
        }
        return "addPatientPage";
    }
}
