package com.bzu.transport_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TransportApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportApiApplication.class, args);
	}

}
