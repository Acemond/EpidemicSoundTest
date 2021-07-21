package com.epidemicsound.test;

import com.epidemicsound.test.spotify.tracks.TracksRestClient;
import com.epidemicsound.test.spotify.tracks.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class TracksTracker {
    private final TracksRestClient tracksRestClient;

    @Autowired
    public TracksTracker(TracksRestClient tracksRestClient) {
        this.tracksRestClient = tracksRestClient;
    }

    public void run() {
        List<Track> tracks = tracksRestClient.getTracks(getTrackIds());

        System.out.println(tracks.size());
        System.exit(0);
    }

    String[] getTrackIds() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:track_ids.txt");

        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader).split("\\r?\\n");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
