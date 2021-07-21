package com.epidemicsound.test.spotify.tracks;

import com.epidemicsound.test.spotify.access.AccessToken;
import com.epidemicsound.test.spotify.access.AccessTokenRestClient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

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

    public List<Track> getTracks() {
        AccessToken token = accessTokenRestClient.authenticate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token.getAccessToken());
        HttpEntity<Void> request = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL + "/tracks")
                .queryParam("ids", String.join(",", getTrackIds()));

        ResponseEntity<Tracks> res =
                restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, request, Tracks.class);

        return Arrays.asList(Objects.requireNonNull(res.getBody()).getTracks());  // FIXME
    }

    String[] getTrackIds() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:track_ids.txt");

        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader).split("\\r?\\n");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
