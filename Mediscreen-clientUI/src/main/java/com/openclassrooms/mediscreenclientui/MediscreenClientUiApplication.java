package com.openclassrooms.mediscreenclientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.mediscreenclientui")
public class MediscreenClientUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediscreenClientUiApplication.class, args);
    }

}
