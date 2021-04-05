package com.example.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class LotteryClientService {
	private String cache = "[4, 8, 15, 16, 23, 42]";

	@Retry(name = "lottery", fallbackMethod = "fallbackGetNumbers")
	
	public String getNumbers() {
		RestTemplate rt = new RestTemplate();
		var numbers = rt.getForEntity(URI.create("http://localhost:2021/numbers"), String.class).getBody();
		cache = numbers;
		return numbers;
	}

	public String fallbackGetNumbers(Throwable t) {
		return cache;
	}
}
