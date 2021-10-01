package com.gebert.sokker.sokkerclient;

import com.gebert.sokker.SokkerController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.gebert.sokker.client"})
@ComponentScan("com.gebert")
public class SokkerClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(SokkerClientApplication.class, args);

  }

  @Bean
  public CommandLineRunner start(SokkerController sokkerController) {
    return (args) -> sokkerController.logIn();
  }


}
