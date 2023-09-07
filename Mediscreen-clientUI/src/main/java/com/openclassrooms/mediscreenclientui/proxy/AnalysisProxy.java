package com.openclassrooms.mediscreenclientui.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign config when running locally
@FeignClient(name = "mediscreen-analysis", url = "localhost:9003")
//Feign config when running with docker
//@FeignClient(name = "mediscreen-analysis", url = "mediscreen-analysis:9003")
public interface AnalysisProxy {
    @GetMapping(value = "/patients/{id}/analysis")
    String getAnalysisFromPatient(@PathVariable("id") Integer id);
}
