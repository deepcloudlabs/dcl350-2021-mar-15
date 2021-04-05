package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.service.LotteryClientService;

@SpringBootApplication
@EnableScheduling
public class StudyResiliencePatternsApplication {
	@Autowired
	private LotteryClientService srv;

	public static void main(String[] args) {
		SpringApplication.run(StudyResiliencePatternsApplication.class, args);
	}

	@Scheduled(fixedRate = 10_000)
	public void run() throws Exception {
		System.err.println(srv.getNumbers());
	}

}
