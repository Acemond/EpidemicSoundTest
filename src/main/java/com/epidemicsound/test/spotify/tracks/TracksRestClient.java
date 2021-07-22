package com.epidemicsound.test.spotify.tracks;

import com.epidemicsound.test.Application;
import com.epidemicsound.test.spotify.access.AccessToken;
import com.epidemicsound.test.spotify.access.AccessTokenRestClient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final String API_URL = config.getString("spotify.rest.api.url");

    private final AccessTokenRestClient accessTokenRestClient;
    private final RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(TracksRestClient.class);

    @Autowired
    public TracksRestClient(AccessTokenRestClient accessTokenRestClient, RestTemplate restTemplate) {
        this.accessTokenRestClient = accessTokenRestClient;
        this.restTemplate = restTemplate;
    }

    public List<SpotifyTrack> getTracks(String[] ids) {
        AccessToken token = accessTokenRestClient.authenticate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token.getAccessToken());

        String url = UriComponentsBuilder.fromHttpUrl(API_URL + "/tracks")
                .queryParam("ids", String.join(",", ids))
                .toUriString();

        ResponseEntity<SpotifyTracks> res = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers), SpotifyTracks.class);

        if (res.getBody() == null) {
            return new ArrayList<>();
        }
        logger.info("Retrieved {} tracks from the API", res.getBody().getTracks().length);
        return Arrays.asList(res.getBody().getTracks());
    }
}
