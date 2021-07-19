package com.epidemicsound.test;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

@SpringBootApplication
public class Application {
    public static final String ACCOUNTS_API_URL = "https://accounts.spotify.com/api";
    public static final String API_URL = "https://api.spotify.com/v1";
    public static final String CLIENT_ID = "1006b92265b6408683db8854959d4e42";
    public static final String CLIENT_SECRET = "582cadd8604547e1ad9ad7ccf955259c";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            List<Track> tracks = getTracks(restTemplate);

            System.out.println(tracks.size());
            System.exit(0);
        };
    }

    List<Track> getTracks(RestTemplate restTemplate) {
        AccessToken token = authenticate(restTemplate);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token.getAccessToken());
        HttpEntity<Void> request = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL + "/tracks")
                .queryParam("ids", String.join(",", getTrackIds()));

        ResponseEntity<Tracks> res =
                restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, request, Tracks.class);

        return Arrays.asList(Objects.requireNonNull(res.getBody()).getTracks());
    }

    AccessToken authenticate(RestTemplate restTemplate) {
        HttpHeaders headers = createHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(ACCOUNTS_API_URL + "/token", request, AccessToken.class);
    }

    HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        byte[] encodedAuth = Base64.encodeBase64((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
        headers.add("Authorization", "Basic " + new String(encodedAuth));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
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
