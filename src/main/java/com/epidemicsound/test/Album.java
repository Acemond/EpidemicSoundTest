package com.epidemicsound.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Album {
    @JsonProperty("release_date")
    private final String releaseDate;

    private Album() {
        releaseDate = "";
    }

    public Album(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Album{" +
                "releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
