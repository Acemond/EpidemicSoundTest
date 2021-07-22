package com.epidemicsound.test.spotify.tracks;

import com.epidemicsound.test.spotify.access.AccessToken;
import com.epidemicsound.test.spotify.access.AccessTokenRestClient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TracksRestClient {
    private final Config config = ConfigFactory.load();
    private final String API_URL = config.getString("spotify.rest.tracks.url");

    private final AccessTokenRestClient accessTokenRestClient;
    private final RestTemplate restTemplate;

    @Autowired
    public TracksRestClient(AccessTokenRestClient accessTokenRestClient, RestTemplate restTemplate) {
        this.accessTokenRestClient = accessTokenRestClient;
        this.restTemplate = restTemplate;
    }

    public List<SpotifyTrack> getTracks(String[] ids) {
        AccessToken token = accessTokenRestClient.authenticate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token.getAccessToken());
        HttpEntity<Void> request = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL + "/tracks")
                .queryParam("ids", String.join(",", ids));

        ResponseEntity<SpotifyTracks> res =
                restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, request, SpotifyTracks.class);

        if (res.getBody() == null) {
            return new ArrayList<>();
        }

        return Arrays.asList(res.getBody().getTracks());
    }
}
