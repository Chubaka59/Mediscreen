package com.openclassrooms.mediscreenclientui.proxy;

import com.openclassrooms.mediscreenclientui.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediscreen-patient", url = "localhost:9001")
public interface PatientProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> getAllPatients();

    @GetMapping(value = "/patients/{id}")
    PatientBean getPatient(@PathVariable int id);

    @PostMapping(value = "patients")
    ResponseEntity<PatientBean> addPatient(@RequestBody PatientBean patient);

    @PutMapping("/patients/{id}")
    ResponseEntity<PatientBean> updatePatient(@RequestBody PatientBean patient, @PathVariable Integer id);

    @GetMapping(value = "/patients/{id}/delete")
    ResponseEntity<PatientBean> deletePatient(@PathVariable Integer id);
}
