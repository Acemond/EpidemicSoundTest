package com.epidemicsound.test.spotify.tracks;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyTrack {
    private final String id;
    private final String name;
    private final SpotifyAlbum album;
    private final String uri;
    @JsonProperty("duration_ms")
    private final int durationMs;
    private final int popularity;

    public SpotifyTrack(String id, String name, SpotifyAlbum spotifyAlbum, String uri, int durationMs, int popularity) {
        this.id = id;
        this.name = name;
        this.album = spotifyAlbum;
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

    public SpotifyAlbum getAlbum() {
        return album;
    }

    @Override
    public String toString() {
        return "SpotifyTrack{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", album=" + album +
                ", uri='" + uri + '\'' +
                ", durationMs=" + durationMs +
                ", popularity=" + popularity +
                '}';
    }
}
