package com.ps.esp.testframework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class TestPaymentGenrator {

	private static final String FILE_NAME = "C://dev//workspace//payment-automation-test-suite//src//main//resources//payment//payment.xml";
	 private static final String SIMPLE_QUEUE = "payment.in";
	 private final JmsTemplate jmsTemplate;

	@Autowired
	public TestPaymentGenrator(JmsTemplate jmsTemplate)
	{
		this.jmsTemplate=jmsTemplate;
	}
	@PostConstruct
	public void send() throws IOException{
		System.out.println("About to send message ::::");
		FileInputStream is = new FileInputStream(FILE_NAME);
		try( BufferedReader br =
		           new BufferedReader( new InputStreamReader(is)))
		   {
		      StringBuilder sb = new StringBuilder();
		      String line;
		      while(( line = br.readLine()) != null ) {
		         sb.append( line );
		      }
		      while(true){
		    	  jmsTemplate.convertAndSend(SIMPLE_QUEUE, sb.toString());
		    	  try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }


		      //JmsResponse<String>.forQueue(sb.toString(), "payment.in");
		   }
	}

}
