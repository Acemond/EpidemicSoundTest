package com.epidemicsound.test.spotify.tracks;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyAlbum {
    @JsonProperty("release_date")
    private final String releaseDate;

    private SpotifyAlbum() {
        releaseDate = "";
    }

    public SpotifyAlbum(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "SpotifyAlbum{" +
                "releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
