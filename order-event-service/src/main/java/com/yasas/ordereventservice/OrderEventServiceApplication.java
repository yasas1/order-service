package com.yasas.ordereventservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@EnableDiscoveryClient
@SpringBootApplication
public class OrderEventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderEventServiceApplication.class, args);
	}
}
