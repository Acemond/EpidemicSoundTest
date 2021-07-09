package com.epidemicsound.test;

import java.time.LocalDate;

public class Track {
    private final String id;
    private final String name;
    private final LocalDate releaseDate;
    private final String uri;
    private final int duration;
    private final int popularity;

    public Track(String id, String name, LocalDate releaseDate, String uri, int duration, int popularity) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.uri = uri;
        this.duration = duration;
        this.popularity = popularity;
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

    public int getDuration() {
        return duration;
    }

    public int getPopularity() {
        return popularity;
    }
}
