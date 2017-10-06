package com.symphony.hackathon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.exceptions.RoomException;
import org.symphonyoss.client.exceptions.StreamsException;
import org.symphonyoss.client.model.Room;
import org.symphonyoss.symphony.clients.model.SymRoomAttributes;
import org.symphonyoss.symphony.clients.model.SymRoomDetail;
import org.symphonyoss.symphony.clients.model.SymRoomSearchCriteria;
import org.symphonyoss.symphony.clients.model.SymRoomSearchResults;
import org.symphonyoss.symphony.clients.model.SymUser;
import org.symphonyoss.symphony.pod.model.MemberInfo;

@Service
public class ChatRoomService  {

 private final Logger logger = LoggerFactory.getLogger(ChatRoomService.class);

 private SymphonyClient symClient;
 
 public ChatRoomService(@Autowired SymphonyClient symClient) {
	 this.symClient=symClient;
 }
@Autowired
 UserHandler userHandler;
 public Room createRoom(String name, String alertDescription) {
	 SymRoomAttributes roomAttributes = new SymRoomAttributes();
	 roomAttributes.setName(name);
	 roomAttributes.setPublic(true);
	 roomAttributes.setDescription(alertDescription);
	 roomAttributes.setDiscoverable(true);
	 try {
		 Room room = symClient.getRoomService().createRoom(roomAttributes);
		 room.setStreamId(room.getId());
		 SymUser user  = userHandler.getUser();
		 MemberInfo mInfo= new MemberInfo();
		 mInfo.setId(user.getId());
		 room.getMembershipList().add(mInfo);
		 logger.info(" Room created  {} {}", room.getId(), room.getRoomDetail().getRoomAttributes().getName());
		 return room;
	} catch (RoomException e) {
		e.printStackTrace();
	}
	 return null;
 }
 
  public SymRoomDetail searchRoom(String name, String description) {
	 
	  try {
			SymRoomSearchCriteria symRoomSearchCriteria = new SymRoomSearchCriteria();
			symRoomSearchCriteria.setQuery(name);
			symRoomSearchCriteria.setCreator(symClient.getLocalUser());
			SymRoomSearchResults symRoomSearchResults = symClient.getStreamsClient().roomSearch(symRoomSearchCriteria,
					0, 100);
			
			for (SymRoomDetail symRoomDetail : symRoomSearchResults.getRooms()) {
				logger.info("Found room {}: {}", symRoomDetail.getRoomAttributes().getName(),
						symRoomDetail.getRoomSystemInfo().getId());
				return symRoomDetail;
			}
			

		} catch (StreamsException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("Unkown Exception", e);
		}
	  return null;
  }
}