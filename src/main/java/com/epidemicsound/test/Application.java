package com.epidemicsound.test;

import com.epidemicsound.test.tracker.TracksTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class Application {
    private final TracksTracker tracksTracker;

    @Autowired
    public Application(TracksTracker tracksTracker) {
        this.tracksTracker = tracksTracker;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Scheduled(cron = "0 31 11 * * *")
    public void cron() {
        tracksTracker.run(LocalDate.now());
    }
}
