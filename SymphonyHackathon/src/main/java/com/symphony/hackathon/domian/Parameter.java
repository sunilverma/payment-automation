package com.symphony.hackathon.domian;

public class Parameter {

	private String id;
	private String client;
	private int limit;
	private String message;
	private String ccy;
	private String trader;
	private String tradeDate;
	public String getClient() {
		return client;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

