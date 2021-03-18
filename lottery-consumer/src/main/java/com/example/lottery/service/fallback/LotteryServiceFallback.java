package com.example.lottery.service.fallback;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lottery.service.client.LotteryService;

@Service
public class LotteryServiceFallback implements LotteryService {

	@Override
	public List<Integer> draw() {
		return List.of(1,2,3,4,5,6);
	}

}
