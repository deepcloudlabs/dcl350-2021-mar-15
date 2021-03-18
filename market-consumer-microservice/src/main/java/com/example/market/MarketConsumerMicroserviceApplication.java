package com.example.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// http://binkurt.blogspot.com/2016/02/rheloele-rabbitmq-kurulumu.html
@SpringBootApplication
public class MarketConsumerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketConsumerMicroserviceApplication.class, args);
	}

}
