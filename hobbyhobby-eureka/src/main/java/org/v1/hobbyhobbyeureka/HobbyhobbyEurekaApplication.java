package org.v1.hobbyhobbyeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HobbyhobbyEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(HobbyhobbyEurekaApplication.class, args);
	}

}
