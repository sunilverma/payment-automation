package com.ps.esc.payment.domain;

import java.io.Serializable;

public class PaymentMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6868377074097423751L;
	private Payment payment;

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}
