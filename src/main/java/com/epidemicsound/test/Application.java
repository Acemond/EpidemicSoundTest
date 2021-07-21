package com.epidemicsound.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private final TracksTracker tracksTracker;

    @Autowired
    public Application(TracksTracker tracksTracker) {
        this.tracksTracker = tracksTracker;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> tracksTracker.run();
    }
}
