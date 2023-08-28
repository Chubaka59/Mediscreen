package com.openclassrooms.mediscreenpatientnote.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonMapper {

    @Autowired
    private ObjectMapper mapper;

    @SneakyThrows
    private String objectToJson(Object o){
        return  mapper.writeValueAsString(o);

    }
}
