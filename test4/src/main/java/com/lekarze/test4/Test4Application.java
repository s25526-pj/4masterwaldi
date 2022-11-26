package com.lekarze.test4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Test4Application {

    public static void main(String[] args) {
        SpringApplication.run(Test4Application.class, args);
    }

}
