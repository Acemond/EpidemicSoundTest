package com.epidemicsound.test;

import com.epidemicsound.test.spotify.tracks.TracksRestClient;
import com.epidemicsound.test.spotify.tracks.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TracksTracker {
    private final TracksRestClient tracksRestClient;

    @Autowired
    public TracksTracker(TracksRestClient tracksRestClient) {
        this.tracksRestClient = tracksRestClient;
    }

    public void run() {
        List<Track> tracks = tracksRestClient.getTracks();

        System.out.println(tracks.size());
        System.exit(0);
    }
}
