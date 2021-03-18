package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;

@Service
public class StandardLotteryService implements LotteryService {
	
	@Override
	public List<Integer> draw() {
		return ThreadLocalRandom.current()
				                .ints(1, 60)
				                .distinct()
				                .limit(6)
				                .sorted()
				                .boxed()
				.collect(Collectors.toList());
	}

}
