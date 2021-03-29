package com.dnc.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DncApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DncApiGatewayApplication.class, args);
	}

}
