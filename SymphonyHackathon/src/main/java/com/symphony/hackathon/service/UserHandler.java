package com.symphony.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.exceptions.UsersClientException;
import org.symphonyoss.symphony.clients.model.SymUser;

@Component
public class UserHandler {

	@Autowired
	private SymphonyClient symClient;
	
	public SymUser getUser() {
		try {
			return symClient.getUsersClient().getUserFromEmail("sunilkrverma@gmail.com");
		} catch (UsersClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
