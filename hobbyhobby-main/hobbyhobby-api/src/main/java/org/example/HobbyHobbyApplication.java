package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan({"org.example.global.config.security.jwt","org.example.domain.auth"})
public class HobbyHobbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HobbyHobbyApplication.class, args);
    }

}
