package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.dto.Ticker;

@Service
public class BinanceRestSyncClient {
	@Value("${binance.rest.http.url}")
	private String url;

	@Scheduled(fixedRate = 3_000)
	public void callTickerPriceService() {
		var restTemplate = new RestTemplate();
		// {"symbol":"BTCUSDT","price":"55181.78000000"}
		var ticker = restTemplate.getForEntity(url, Ticker.class).getBody();
		System.err.println(Thread.currentThread().getName() + ": " + ticker);
	}
	
	
	@Scheduled(fixedRate = 3_000)
	public void asyncCallTickerPriceService() {
		var asyncRestTemplate = new AsyncRestTemplate();
		// {"symbol":"BTCUSDT","price":"55181.78000000"}
		asyncRestTemplate.getForEntity(url, Ticker.class)
		                 .addCallback(
		                		 responseEntity -> System.err.println(Thread.currentThread().getName() + ": " + responseEntity.getBody()) ,
		                		 error -> System.err.println(error.getMessage())
		                );
	}
	
}
