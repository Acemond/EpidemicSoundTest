package com.epidemicsound.test.spotify.tracks;

import java.util.Arrays;

public class Tracks {
    private final Track[] tracks;

    public Tracks() {
        this.tracks = new Track[0];
    }

    public Tracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public Track[] getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return "Tracks{" +
                "tracks=" + Arrays.toString(tracks) +
                '}';
    }
}
