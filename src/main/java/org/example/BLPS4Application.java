package org.example;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;;


@SpringBootApplication
@EnableProcessApplication
public class BLPS4Application {
    public static void main(String[] args) {
        SpringApplication.run(BLPS4Application.class, args);
    }
}
