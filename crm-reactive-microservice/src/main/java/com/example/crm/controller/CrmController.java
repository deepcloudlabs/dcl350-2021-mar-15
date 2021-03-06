package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.CrmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
@CrossOrigin
public class CrmController {
	@Autowired
	private CrmService crmService;
	
	@GetMapping("{identity}")
	public Mono<CustomerDocument> findByIdentity(@PathVariable String identity){
		return crmService.findCustomerById(identity);
	}
	
	@GetMapping
	public Flux<CustomerDocument> findAllByPaging(int page,int size){
		return crmService.findCustomersByPage(page,size);
	}
}
