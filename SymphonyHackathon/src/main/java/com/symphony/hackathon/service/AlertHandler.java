package com.symphony.hackathon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.exceptions.MessagesException;
import org.symphonyoss.client.exceptions.RoomException;
import org.symphonyoss.client.model.Room;
import org.symphonyoss.symphony.clients.model.SymRoomDetail;

import com.symphony.hackathon.domian.Alert;

@Component
public class AlertHandler  {

	private final Logger logger = LoggerFactory.getLogger(AlertHandler.class);
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private AlertPublisher alertPublisher;
	@Autowired
	UserHandler userHandler;
	
	public void handleAlert(Alert alert) throws MessagesException, RoomException {
		logger.info("Room Search starting... >>>> " );
		SymRoomDetail foundRoom=chatRoomService.searchRoom(alert.getType(), alert.getDescription());
		Room room=null;
		if(foundRoom==null) {
			room=chatRoomService.createRoom(alert.getType(), alert.getDescription());
			if(room!=null)
				try {
					alertPublisher.publishAletToSymphony(room,alert);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else {
			alertPublisher.publishAletToSymphony(foundRoom, alert);
		}
	}
}