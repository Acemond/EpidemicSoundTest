package com.epidemicsound.test.tracker;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Track {
    @Id
    private final String id;
    private final String name;
    private final LocalDate releaseDate;
    private final String uri;
    private final Integer durationMs;

    private Track() {
        id = null;
        name = null;
        releaseDate = null;
        uri = null;
        durationMs = null;
    }

    public Track(String id, String name, LocalDate releaseDate, String uri, int durationMs) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.uri = uri;
        this.durationMs = durationMs;
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

    public Integer getDurationMs() {
        return durationMs;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", uri='" + uri + '\'' +
                ", durationMs=" + durationMs +
                '}';
    }
}
