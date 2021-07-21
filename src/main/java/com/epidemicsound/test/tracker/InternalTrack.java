package com.epidemicsound.test.tracker;

import com.epidemicsound.test.spotify.tracks.Track;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class InternalTrack {
    @Id
    private final String id;
    private final String name;
    private final LocalDate releaseDate;
    private final String uri;
    private final int durationMs;

    private InternalTrack() {
        id = null;
        name = null;
        releaseDate = null;
        uri = null;
        durationMs = 0;
    }

    public InternalTrack(Track track) {
        this(track.getId(),
                track.getName(),
                LocalDate.parse(track.getAlbum().getReleaseDate()),
                track.getUri(),
                track.getDurationMs());
    }

    public InternalTrack(String id, String name, LocalDate releaseDate, String uri, int durationMs) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.uri = uri;
        this.durationMs = durationMs;
    }

    public static InternalTrack fromSpotifyTrack(Track track) {
        return new InternalTrack(track.getId(),
                track.getName(),
                LocalDate.parse(track.getAlbum().getReleaseDate()),
                track.getUri(),
                track.getDurationMs());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getUri() {
        return uri;
    }

    public int getDurationMs() {
        return durationMs;
    }
}
