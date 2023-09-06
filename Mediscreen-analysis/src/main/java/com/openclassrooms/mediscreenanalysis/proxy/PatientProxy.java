package com.openclassrooms.mediscreenanalysis.proxy;


import com.openclassrooms.mediscreenanalysis.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mediscreen-patient", url = "localhost:9001")
public interface PatientProxy {

    @GetMapping(value = "/patients/{id}")
    PatientBean getPatient(@PathVariable int id);
}
