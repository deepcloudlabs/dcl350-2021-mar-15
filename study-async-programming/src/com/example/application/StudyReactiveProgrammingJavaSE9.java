package com.example.application;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

public class StudyReactiveProgrammingJavaSE9 {

	public static void main(String[] args) throws InterruptedException {
		System.err.println("Application is running...");
		SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>();
		Flow.Subscriber<TradeEvent> slowSubscriber = new AlgoTrader();
		Flow.Subscriber<TradeEvent> fastSubscriber = new SignalProcessor();
		publisher.subscribe(slowSubscriber);
		publisher.subscribe(fastSubscriber);
		var tradeEvents = List.of(new TradeEvent("ORCL", 100.0, 50), new TradeEvent("IBM", 70.0, 250),
				new TradeEvent("MSFT", 110.0, 500), new TradeEvent("ORCL", 102.0, 250),
				new TradeEvent("MSFT", 112.0, 2500));
		tradeEvents.forEach(publisher::submit);
		TimeUnit.SECONDS.sleep(30);
	}

}

class AlgoTrader implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("Slow Subscriber: " + event);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("Error: " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("Stream is completed.");
	}

}

class SignalProcessor implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		System.err.println("Fast Subscriber: " + event);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("Error: " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("Stream is completed.");
	}

}
