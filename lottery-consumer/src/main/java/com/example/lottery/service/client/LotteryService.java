package com.example.lottery.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.lottery.service.fallback.LotteryServiceFallback;

@FeignClient(name = "lottery-v1" , fallback = LotteryServiceFallback.class )
public interface LotteryService {
	@GetMapping("/lottery/api/v1/numbers")
	List<Integer> draw();
}