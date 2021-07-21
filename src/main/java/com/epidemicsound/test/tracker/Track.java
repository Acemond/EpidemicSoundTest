package com.epidemicsound.test.tracker;

import com.epidemicsound.test.spotify.tracks.SpotifyTrack;

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
    private final int durationMs;

    private Track() {
        id = null;
        name = null;
        releaseDate = null;
        uri = null;
        durationMs = 0;
    }

    public Track(SpotifyTrack spotifyTrack) {
        this(spotifyTrack.getId(),
                spotifyTrack.getName(),
                LocalDate.parse(spotifyTrack.getAlbum().getReleaseDate()),
                spotifyTrack.getUri(),
                spotifyTrack.getDurationMs());
    }

    public Track(String id, String name, LocalDate releaseDate, String uri, int durationMs) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.uri = uri;
        this.durationMs = durationMs;
    }

    public static Track fromSpotifyTrack(SpotifyTrack spotifyTrack) {
        return new Track(spotifyTrack.getId(),
                spotifyTrack.getName(),
                LocalDate.parse(spotifyTrack.getAlbum().getReleaseDate()),
                spotifyTrack.getUri(),
                spotifyTrack.getDurationMs());
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
