package com.openclassrooms.mediscreenclientui.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mediscreen-analysis")
public interface AnalysisProxy {
    @GetMapping(value = "/patients/{id}/analysis")
    String getAnalysisFromPatient(@PathVariable("id") Integer id);
}
