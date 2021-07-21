package com.epidemicsound.test.spotify.tracks;

import java.util.Arrays;

public class SpotifyTracks {
    private final SpotifyTrack[] tracks;

    public SpotifyTracks() {
        this.tracks = new SpotifyTrack[0];
    }

    public SpotifyTracks(SpotifyTrack[] spotifyTracks) {
        this.tracks = spotifyTracks;
    }

    public SpotifyTrack[] getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return "SpotifyTracks{" +
                "tracks=" + Arrays.toString(tracks) +
                '}';
    }
}
