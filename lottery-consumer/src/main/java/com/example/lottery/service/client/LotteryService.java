package com.example.lottery.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "lottery-v1" )
public interface LotteryService {
	@GetMapping("/lottery/api/v1/numbers")
	List<Integer> draw();
}