package com.symphony.hackathon.listener;



import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.symphonyoss.client.exceptions.MessagesException;
import org.symphonyoss.client.exceptions.RoomException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.symphony.hackathon.domian.Alert;
import com.symphony.hackathon.domian.AlertMessage;
import com.symphony.hackathon.service.AlertHandler;

@Service
public class AlertListener {
	
	private AlertHandler alertHandler;
	private final Logger logger = LoggerFactory.getLogger(AlertListener.class);
	
	public AlertListener (@Autowired AlertHandler alertHandler) {
	 this.alertHandler=alertHandler;	
	}
	
	@JmsListener(destination="alert.in")
	public void recieveMessage(final Message alertMessage) throws JMSException, JsonParseException, JsonMappingException, IOException, MessagesException, RoomException {
		if(alertMessage instanceof TextMessage) {
			logger.debug(((TextMessage)alertMessage).getText());
			String messageData =((TextMessage)alertMessage).getText();
			ObjectMapper mapper = new ObjectMapper();
			AlertMessage alertMessageObj = mapper.readValue(messageData, AlertMessage.class);
			alertHandler.handleAlert(alertMessageObj.getAlert());
		}
	}
}