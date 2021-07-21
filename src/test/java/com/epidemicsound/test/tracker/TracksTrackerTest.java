package com.epidemicsound.test.tracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TracksTrackerTest {
    @Autowired
    private TracksTracker tracksTracker;

    @Test
    void run() {
        tracksTracker.run();
    }
}