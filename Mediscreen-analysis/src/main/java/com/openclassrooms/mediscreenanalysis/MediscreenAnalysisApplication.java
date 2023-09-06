package com.openclassrooms.mediscreenanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.mediscreenanalysis")
public class MediscreenAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenAnalysisApplication.class, args);
	}

}
