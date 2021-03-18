package com.example.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// {"e":"trade","E":1615986000394,"s":"BTCUSDT","t":712273828,"p":"55151.00000000","q":"0.06009200","b":5260404500,"a":5260404552,"T":1615986000393,"m":true,"M":true}

public class Trade {
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	public Trade() {
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Trade [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
}
