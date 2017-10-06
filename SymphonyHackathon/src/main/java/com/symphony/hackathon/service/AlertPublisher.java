package com.symphony.hackathon.service;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.exceptions.MessagesException;
import org.symphonyoss.client.exceptions.RoomException;
import org.symphonyoss.client.model.Room;
import org.symphonyoss.symphony.clients.model.SymMessage;
import org.symphonyoss.symphony.clients.model.SymRoomDetail;
import org.symphonyoss.symphony.clients.model.SymUser;
import org.symphonyoss.symphony.pod.model.MemberInfo;
import org.symphonyoss.symphony.pod.model.MembershipList;
import org.symphonyoss.symphony.pod.model.Stream;

import com.symphony.hackathon.domian.Alert;

@Service
public class AlertPublisher {

	private SymphonyClient symClient;

	private final Logger logger = LoggerFactory.getLogger(AlertPublisher.class);
	
	public AlertPublisher(@Autowired SymphonyClient symClient) {
		this.symClient = symClient;
	}

	public void publishAletToSymphony(Room room, Alert alert) throws MessagesException, RoomException {
		SymMessage aMessage = new SymMessage();
		aMessage.setFormat(SymMessage.Format.MESSAGEML);
		aMessage.setMessage(getMessage(alert));
		logger.info("Sedning message ....");
		symClient.getMessageService().sendMessage(room, aMessage);
	}

	@Autowired
	UserHandler userHandler;

	public void publishAletToSymphony(SymRoomDetail room, Alert alert) throws MessagesException, RoomException {
		Stream stream = new Stream();
		stream.setId(room.getRoomSystemInfo().getId());
		Room tempRoom = new Room();
		tempRoom.setStream(stream);
		tempRoom.setId(stream.getId());
		SymUser user = userHandler.getUser();
		MemberInfo mInfo = new MemberInfo();
		mInfo.setId(user.getId());
		MembershipList x = new MembershipList();
		x.add(mInfo);
		tempRoom.setMembershipList(x);
		SymMessage aMessage = new SymMessage();
		aMessage.setFormat(SymMessage.Format.MESSAGEML);
		aMessage.setMessage(getMessage(alert));
		symClient.getMessageService().sendMessage(tempRoom, aMessage);
	}

	public String getMessage(Alert alert) {
		

		VelocityEngine ve = new VelocityEngine();
		
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	    ve.init();
		Template t = ve.getTemplate("templates/message.vm");
		VelocityContext context = new VelocityContext();
		context.put("message", alert.getParameter().getMessage());
		context.put("client", alert.getParameter().getClient());
		context.put("trader", alert.getParameter().getTrader());
		context.put("tradeDate", alert.getParameter().getTradeDate());
		context.put("ccy", alert.getParameter().getCcy());
		context.put("limit", alert.getParameter().getLimit());
		context.put("tradeId",alert.getParameter().getId());
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
}
