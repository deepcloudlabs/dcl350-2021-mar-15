package com.example.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.market.events.TradeEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaTradeListenerService {
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	@Autowired
	private ObjectMapper mapper;

	@KafkaListener(topics = "market")
	public void listenTradeEvents(String trade) {
		System.err.println("New trade event has arrived from kafka topic: " + trade);
		try {
			var tradeEvent = mapper.readValue(trade, TradeEvent.class);
			eventPublisher.publishEvent(tradeEvent);
		} catch (JsonProcessingException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
