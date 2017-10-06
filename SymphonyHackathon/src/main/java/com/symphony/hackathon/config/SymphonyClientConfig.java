package com.symphony.hackathon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.SymphonyClientFactory;

@Configuration
public class SymphonyClientConfig {
	@Value("${sessionauth.url}")
	private String sessionauth;
	@Value("${keyauth.url}")
	private String keyauth;

	@Value("${user.cert.file}")
	private String userCert;

	@Value("${user.cert.password}")
	private String userCertPass;

	@Value("${truststore.file}")
	private String truststore;

	@Value("${agent.url}")
	private String agent;

	@Value("${pod.url}")
	private String pod;

	@Bean
	public SymphonyClient getSymClient() {
		System.setProperty("sessionauth.url", sessionauth);
		System.setProperty("keyauth.url", keyauth);
		System.setProperty("symphony.agent.pod.url", pod);
		System.setProperty("symphony.agent.agent.url", agent);
		SymphonyClient symClient = SymphonyClientFactory.getClient(
                  SymphonyClientFactory.TYPE.V4,"cs-bot@symphony.foundation",userCert,userCertPass,truststore,"changeit");
		return symClient;
        
	}

}
