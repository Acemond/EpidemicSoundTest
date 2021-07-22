package com.epidemicsound.test.tracker;

import com.epidemicsound.test.spotify.tracks.SpotifyAlbum;
import com.epidemicsound.test.spotify.tracks.SpotifyTrack;
import com.epidemicsound.test.spotify.tracks.TracksRestClient;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TracksTrackerTest {
    @Autowired
    TracksRepository tracksRepository;

    @Autowired
    PopularityRepository popularityRepository;

    @MockBean
    TracksRestClient restClient;

    @Autowired
    private TracksTracker tracksTracker;

    @Test
    void run() {
        List<SpotifyTrack> input = Collections.singletonList(
                new SpotifyTrack("id", "name", new SpotifyAlbum("2021-07-22"), "uri", 42, 100));

        LocalDate executionDate = LocalDate.of(2021, 7, 23);
        Mockito.when(restClient.getTracks(Mockito.any())).thenReturn(input);

        tracksTracker.run(executionDate);

        List<Track> tracksResult = Lists.newArrayList(tracksRepository.findAll());
        List<Popularity> popResult = Lists.newArrayList(popularityRepository.findAll());

        assertThat(tracksResult.size()).isEqualTo(1);
        assertThat(popResult.size()).isEqualTo(1);

        assertThat(tracksResult.get(0)).usingRecursiveComparison().isEqualTo(
                new Track("id", "name", LocalDate.of(2021, 7, 22), "uri", 42));
        assertThat(popResult.get(0)).usingRecursiveComparison().isEqualTo(
                new Popularity("id", 100, executionDate));
    }
}