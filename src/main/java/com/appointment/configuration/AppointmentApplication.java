package com.appointment.configuration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.appointment.*"})
public class AppointmentApplication {
    public static void main(String... args){
        ConfigurableApplicationContext context = SpringApplication.run(AppointmentApplication.class);
    }
}

