package com.TugasAkhir.DocIn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DocInApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocInApplication.class, args);
	}

}
