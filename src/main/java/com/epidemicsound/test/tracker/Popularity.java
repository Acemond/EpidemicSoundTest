package com.epidemicsound.test.tracker;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(indexes = @Index(columnList = "atDate"))
public class Popularity {
    @Id
    private final String id;
    private final String trackId;
    private final Integer popularity;
    private final LocalDate atDate;

    private Popularity() {
        id = null;
        trackId = null;
        popularity = null;
        atDate = null;
    }

    public Popularity(String trackId, int popularity, LocalDate atDate) {
        this.id = atDate + "|" + trackId;
        this.trackId = trackId;
        this.popularity = popularity;
        this.atDate = atDate;
    }

    public String getId() {
        return id;
    }

    public String getTrackId() {
        return trackId;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public LocalDate getAtDate() {
        return atDate;
    }

    @Override
    public String toString() {
        return "Popularity{" +
                "id='" + id + '\'' +
                ", trackId='" + trackId + '\'' +
                ", popularity=" + popularity +
                ", atDate=" + atDate +
                '}';
    }
}
