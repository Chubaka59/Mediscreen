package com.openclassrooms.mediscreenclientui.proxy;

import com.openclassrooms.mediscreenclientui.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "mediscreen-patient", url = "localhost:9001")
public interface PatientProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> getallPatients();

    @GetMapping(value = "/patients/{id}")
    PatientBean getPatient(@PathVariable int id);

    @PostMapping(value = "patients")
    ResponseEntity<PatientBean> addPatient(@RequestBody PatientBean patient);
}
