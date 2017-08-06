package com.ps.esc.payment.domain;

import java.io.Serializable;

public class PaymentDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5175829181278611348L;
	private String amount;
	private Integer id;
	private String valueDate;
	private Integer version;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
