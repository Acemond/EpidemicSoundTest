package com.epidemicsound.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {
    private final String id;
    private final String name;
    private final Album album;
    private final String uri;
    @JsonProperty("duration_ms")
    private final int durationMs;
    private final int popularity;

    public Track(String id, String name, Album album, String uri, int durationMs, int popularity) {
        this.id = id;
        this.name = name;
        this.album = album;
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

    public String getUri() {
        return uri;
    }

    public int getDurationMs() {
        return durationMs;
    }

    public int getPopularity() {
        return popularity;
    }

    public Album getAlbum() {
        return album;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", album=" + album +
                ", uri='" + uri + '\'' +
                ", durationMs=" + durationMs +
                ", popularity=" + popularity +
                '}';
    }
}
