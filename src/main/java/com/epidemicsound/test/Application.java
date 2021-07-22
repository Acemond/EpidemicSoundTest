package com.epidemicsound.test;

import com.epidemicsound.test.tracker.TracksTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    public Application(TracksTracker tracksTracker) {
        this.tracksTracker = tracksTracker;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Scheduled(cron = "0 0 4 * * *")
    public void cron() {
        logger.info("Starting scheduled application");
        tracksTracker.run(LocalDate.now());
    }
}
