package com.example.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.market.dto.Trade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TradeKafkaService {
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate; // message broker
	@Autowired
	private ObjectMapper mapper;
	
	@EventListener
	public void listenTradeEvents(Trade trade) {
		System.err.println("Application event: "+trade);
		try {
			var json = mapper.writeValueAsString(trade);
			kafkaTemplate.send("market", json);
		} catch (JsonProcessingException e) {
			System.err.println("Error in sending trade event: "+e.getMessage());
		}
	}
}
