package com.ps.esc.payment.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
public class Payment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4962817906535067464L;
	private PaymentDetails paymentDetails;
	private PaymentHeader paymentHeader;
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public PaymentHeader getPaymentHeader() {
		return paymentHeader;
	}
	public void setPaymentHeader(PaymentHeader paymentHeader) {
		this.paymentHeader = paymentHeader;
	}

}
