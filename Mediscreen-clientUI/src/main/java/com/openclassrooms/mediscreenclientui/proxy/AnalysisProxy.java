package com.openclassrooms.mediscreenclientui.proxy;

import com.openclassrooms.mediscreenclientui.bean.AnalysisBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mediscreen-analysis", url = "localhost:9003")
public interface AnalysisProxy {
    @PostMapping(value = "/patients/{id}/analysis")
    String getAnalysisFromPatient(@RequestBody AnalysisBean analysisBean, @PathVariable("id") Integer id);
}
