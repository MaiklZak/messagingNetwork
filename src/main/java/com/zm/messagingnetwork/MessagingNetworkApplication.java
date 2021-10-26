package com.zm.messagingnetwork;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingNetworkApplication {

	public static void main(String[] args) {
		Sentry.capture("Application started");
		SpringApplication.run(MessagingNetworkApplication.class, args);
	}

}
