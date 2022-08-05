package com.yasas.orderserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OrderServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceRegistryApplication.class, args);
	}

}
