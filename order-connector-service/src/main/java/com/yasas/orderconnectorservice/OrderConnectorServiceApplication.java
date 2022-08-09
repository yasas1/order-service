package com.yasas.orderconnectorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderConnectorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderConnectorServiceApplication.class, args);
	}

}
