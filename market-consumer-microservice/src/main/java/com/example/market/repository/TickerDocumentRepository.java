package com.example.market.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.market.document.TickerDocument;

public interface TickerDocumentRepository extends MongoRepository<TickerDocument, String>{

}
