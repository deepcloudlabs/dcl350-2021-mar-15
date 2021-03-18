package com.example.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.market.events.TradeEvent;

@Service
public class TradeEventWebSocketService {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@EventListener
	public void listenTradeEvents(TradeEvent event) {
		messagingTemplate.convertAndSend("/topic/changes", event);
	}
}
