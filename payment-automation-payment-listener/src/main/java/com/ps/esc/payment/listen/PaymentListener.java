package com.ps.esc.payment.listen;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	@JmsListener(destination = "payment.in")
	@SendTo("payment.out")
	public String receiveMessage(final Message xmlMessage) throws JMSException{
		String messageData = null;
		System.out.println("Received message " + xmlMessage);
		if(xmlMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)xmlMessage;
			messageData = textMessage.getText();
			JSONObject xmlJSONObj = XML.toJSONObject(messageData);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			return jsonPrettyPrintString;
		}
		return "";
	}
}
