package com.epidemicsound.test.tracker;

import com.epidemicsound.test.spotify.tracks.SpotifyTrack;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Popularity {
    @Id
    private final String id;
    private final String trackId;
    private final int popularity;
    private final LocalDate atDate;

    private Popularity() {
        id = null;
        trackId = null;
        popularity = 0;
        atDate = LocalDate.now();
    }

    public Popularity(String trackId, int popularity, LocalDate atDate) {
        this.id = atDate + "|" + trackId;
        this.trackId = trackId;
        this.popularity = popularity;
        this.atDate = atDate;
    }

    public static Popularity fromSpotifyTrack(SpotifyTrack spotifyTrack) {
        return new Popularity(spotifyTrack.getId(), spotifyTrack.getPopularity(), LocalDate.now());
    }

    public String getId() {
        return id;
    }

    public String getTrackId() {
        return trackId;
    }

    public int getPopularity() {
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
