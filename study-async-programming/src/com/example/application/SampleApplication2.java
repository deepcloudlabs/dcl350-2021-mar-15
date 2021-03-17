package com.example.application;

import java.util.concurrent.TimeUnit;

import com.example.service.LotteryAsyncService;

public class SampleApplication2 {

	public static void main(String[] args) throws InterruptedException {
		var lotteryService = new LotteryAsyncService();
		System.err.println("Just started...");
		lotteryService.asyncDraw(60, 6)
		              .thenAccept( numbers -> System.err.println(Thread.currentThread().getName()+": "+numbers) );
		for (var i=0;i<10;++i)
			System.err.println(Thread.currentThread().getName()+" - i: "+i);
		TimeUnit.SECONDS.sleep(10);
	}

}
