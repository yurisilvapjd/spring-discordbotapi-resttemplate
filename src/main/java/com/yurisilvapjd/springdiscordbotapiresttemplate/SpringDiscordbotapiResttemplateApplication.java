package com.yurisilvapjd.springdiscordbotapiresttemplate;

import com.yurisilvapjd.springdiscordbotapiresttemplate.components.DiscordBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDiscordbotapiResttemplateApplication {

	@Autowired
	DiscordBot discordBot;

	public static void main(String[] args) {
		SpringApplication.run(SpringDiscordbotapiResttemplateApplication.class, args);
	}

}
