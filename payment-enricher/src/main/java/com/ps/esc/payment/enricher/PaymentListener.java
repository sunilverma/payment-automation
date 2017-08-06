package com.ps.esc.payment.enricher;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.esc.payment.domain.Payment;

@Component
public class PaymentListener {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	@JmsListener(destination = "enrich.in")
	public void receiveMessage(final Message payment) throws JMSException, JsonParseException, JsonMappingException, IOException{
		System.out.println("Received message " + payment.getClass());
		if(payment instanceof TextMessage) {
			TextMessage objectMessage = (TextMessage)payment;
			
			System.out.println(objectMessage + "\n Enriched ::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n\n");
		}
	}
}
