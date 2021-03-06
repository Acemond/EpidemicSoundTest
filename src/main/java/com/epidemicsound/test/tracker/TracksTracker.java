package com.epidemicsound.test.tracker;

import com.epidemicsound.test.spotify.tracks.SpotifyTrack;
import com.epidemicsound.test.spotify.tracks.TracksRestClient;
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
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TracksTracker {
    private final TracksRestClient tracksRestClient;
    private final TracksRepository tracksRepository;
    private final PopularityRepository popularityRepository;

    @Autowired
    public TracksTracker(TracksRestClient tracksRestClient, TracksRepository tracksRepository, PopularityRepository popularityRepository) {
        this.tracksRestClient = tracksRestClient;
        this.tracksRepository = tracksRepository;
        this.popularityRepository = popularityRepository;
    }

    public void run(LocalDate atDate) {
        List<SpotifyTrack> spotifyTracks = tracksRestClient.getTracks(getTrackIds());

        tracksRepository.saveAll(spotifyTracks.stream().map(SpotifyTrack::toTrack).collect(Collectors.toList()));
        popularityRepository.saveAll(spotifyTracks.stream().map(t -> t.toPopularity(atDate)).collect(Collectors.toList()));
    }

    String[] getTrackIds() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:track_ids.txt");

        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader).split("\\r?\\n");
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to parse tracks id file.", e);
        }
    }
}
