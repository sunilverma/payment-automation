package com.ps.esc.payment.listen;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.esc.payment.domain.Payment;
import com.ps.esc.payment.domain.PaymentMessage;


@Component
public class PaymentListener {

	private MongoTemplate mongoTemplate;
	@Autowired
	public PaymentListener(MongoTemplate mongoTemplate){
		this.mongoTemplate=mongoTemplate;
	}
	@JmsListener(destination = "payment.out")//JmsResponse<Message<Payment>> 
	public JmsResponse<org.springframework.messaging.Message<String>> receiveMessage(final Message jsonMessage) throws JMSException, JsonParseException, JsonMappingException, IOException{
		String messageData = null;

		org.springframework.messaging.Message<String> response =null;
		System.err.println(jsonMessage.getClass().getName());
		if(jsonMessage instanceof TextMessage) {
			System.out.println("Received message " + jsonMessage);
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			ObjectMapper mapper = new ObjectMapper();
			PaymentMessage paymentMessage = mapper.readValue(messageData, PaymentMessage.class);
			Payment payment=paymentMessage.getPayment();
			mongoTemplate.save(payment);
			boolean locked=false;
			response = MessageBuilder
		            .withPayload(messageData)
		            .setHeader("code", 1234)
		            .build();
			if(locked){
				
			}
			System.out.println(">>> "+payment.getPaymentDetails().getId());
			System.out.println(">>> "+payment.getPaymentDetails().getAmount());
		}
		return JmsResponse.forQueue(response, "enrich.in");
	}
}