package com.epidemicsound.test;

import com.epidemicsound.test.spotify.tracks.Track;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InternalTrack {
    @Id
    private final String id;
    private final String name;
    private final String releaseDate;
    private final String uri;
    private final int durationMs;
    private final int popularity;

    private InternalTrack() {
        id = "";
        name = "";
        releaseDate = "";
        uri = "";
        durationMs = 0;
        popularity = 0;
    }

    public InternalTrack(Track track) {
        this(track.getId(),
                track.getName(),
                track.getAlbum().getReleaseDate(),
                track.getUri(),
                track.getDurationMs(),
                track.getPopularity()
        );
    }

    public InternalTrack(String id, String name, String releaseDate, String uri, int durationMs, int popularity) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.uri = uri;
        this.durationMs = durationMs;
        this.popularity = popularity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUri() {
        return uri;
    }

    public int getDurationMs() {
        return durationMs;
    }

    public int getPopularity() {
        return popularity;
    }
}
