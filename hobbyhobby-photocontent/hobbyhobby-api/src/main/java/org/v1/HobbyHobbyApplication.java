package org.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableFeignClients
public class HobbyHobbyApplication {
    public static void main(String[] args) {
        SpringApplication.run(HobbyHobbyApplication.class, args);
    }
}
