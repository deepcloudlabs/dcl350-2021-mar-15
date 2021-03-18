package com.example.market.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.market.dto.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BinanceWebsocketClient implements WebSocketHandler {
	@Value("${binance.websocket.url}")
	private String url;
	@Autowired
	private WebSocketClient websocketClient;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@PostConstruct
	public void init() {
		websocketClient.doHandshake(this, url);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the websokcet server!");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var trade = mapper.readValue(message.getPayload().toString(),Trade.class);
		eventPublisher.publishEvent(trade);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err.println("Error has occured: " + exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the websokcet server!");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
