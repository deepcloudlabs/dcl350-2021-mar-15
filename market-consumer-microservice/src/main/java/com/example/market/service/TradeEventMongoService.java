package com.example.market.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.market.document.TickerDocument;
import com.example.market.events.TradeEvent;
import com.example.market.repository.TickerDocumentRepository;

@Service
public class TradeEventMongoService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TickerDocumentRepository tickerDocRepo;

	@EventListener
	public void listenTradeEvents(TradeEvent event) {
		tickerDocRepo.save(modelMapper.map(event, TickerDocument.class));
	}
}
