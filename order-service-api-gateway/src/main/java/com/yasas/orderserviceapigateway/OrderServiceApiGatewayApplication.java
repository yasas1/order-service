package com.yasas.orderserviceapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderServiceApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApiGatewayApplication.class, args);
	}

}
